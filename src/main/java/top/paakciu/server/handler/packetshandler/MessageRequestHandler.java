package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgOffline;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.MessageRequestPacket;
import top.paakciu.protocal.packet.MessageResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.NormalMessageOfflineService;
import top.paakciu.service.NormalMessageService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ChannelUser;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author paakciu
 * @ClassName: MessageRequestHandler
 * @date: 2021/3/3 18:09
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    //单例
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        NettyServer.executor.submit(()->{
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            //
            ChannelUser channelUser= AttributesHelper.getChannelUser(ctx.channel());
            //拿到消息接收方的 channel
            Channel toChannel = AttributesHelper.getChannel(messageRequestPacket.getToUserId());
            // 不能自己发给自己
            if(toChannel==ctx.channel()){
                String str="服务器拒绝！不能发送给自己！";
                ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
                errorMessagePacket.setSuccess(false);
                errorMessagePacket.setErrorCode(PacketsCommandMapping.MESSAGE_REQUEST);
                errorMessagePacket.setReason(str);
                ctx.channel().writeAndFlush(errorMessagePacket);
                System.out.println(str);
                return ;
            }
            // 完成消息的封装
            Date thisTime=new Date();
            //转发客户端消息
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setFromUserId(channelUser.getUserId());
            messageResponsePacket.setFromUserName(channelUser.getUserName());
            messageResponsePacket.setDate(thisTime);
            messageResponsePacket.setMessage(messageRequestPacket.getMessage());
            messageResponsePacket.setToUserId(messageRequestPacket.getToUserId());

            System.out.println("从客户端"+channelUser.getUserId()+"到客户端"+messageRequestPacket.getToUserId());
            System.out.println("频道hashcode:"+ctx.channel()+"---------->"+toChannel);

            //准备好持久化pojo对象
            NormalMsg normalMsg=new NormalMsg();
            //normalMsg.setNmId(channelUser.getUserId());
            normalMsg.setNmFromid(channelUser.getUserId());
            normalMsg.setNmToid(messageRequestPacket.getToUserId());
            normalMsg.setNmMsg(messageRequestPacket.getMessage());
            normalMsg.setNmTime(thisTime);

            //使用线程池进行
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            System.out.println("准备执行线程池");
            delayPersistenceAndSend(executor,0,ctx.channel(),toChannel,normalMsg,messageResponsePacket);
            System.out.println("线程池执行关闭");
            //线程池用完记得关闭
            executor.shutdown();

        });
    }
    public void delayPersistenceAndSend(ScheduledExecutorService executor,int delay,Channel fromChannel,Channel toChannel,NormalMsg normalMsg,MessageResponsePacket messageResponsePacket){
        if(delay>2){
            //如果重试大于了2次，就不再尝试持久化了，直接向客户端报告服务器忙或者服务器数据库异常。
            ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
            errorMessagePacket.setSuccess(false);
            errorMessagePacket.setErrorCode(PacketsCommandMapping.MESSAGE_REQUEST);
            errorMessagePacket.setReason("服务器忙或者服务器数据库异常");
            fromChannel.writeAndFlush(errorMessagePacket);
            //executor.shutdown();
            System.out.println("服务器忙或者服务器数据库异常");
            return ;
        }
        executor.schedule(()->{
            System.out.println("进入schedule");
            int ref= NormalMessageService.addNomalMessage(normalMsg);
            if(ref==0||ref<0){
                //这里是持久化失败的操作，应该使用队列来重做这个任务。每次多延迟一秒后，重试
                delayPersistenceAndSend(executor, delay+1,fromChannel,toChannel, normalMsg,messageResponsePacket);
            }else{
                //这里是持久化成功后，直接转发该通知
                // 返回持久化消息的id
                messageResponsePacket.setMessageId(normalMsg.getId());
                send(fromChannel,toChannel, normalMsg, messageResponsePacket);
            }
        },delay, TimeUnit.SECONDS);
    }

    public void send(Channel fromChannel,Channel toChannel,NormalMsg normalMsg,MessageResponsePacket messageResponsePacket){
        System.out.println("进入send");
        if (toChannel != null && AttributesHelper.hasLogin(toChannel)) {
            System.out.print("发送到在线客户端  ");
            toChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
                if(future.isSuccess()){
                    System.out.println("发送成功");
                }else{
                    System.out.println("发送失败");
                }
            });
            System.out.print("发送到原发送者在线客户端  ");
            fromChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
                if(future.isSuccess()){
                    System.out.println("发送成功");
                }else{
                    System.out.println("发送失败");
                }
            });
        } else {
            System.err.println("[" + normalMsg.getNmToid() + "] 不在线，发送失败!");
            // 加入到离线消息
            NormalMsgOffline msgOffline=new NormalMsgOffline();
            msgOffline.setMsgid(normalMsg.getId());
            msgOffline.setNmoToid(normalMsg.getNmToid());
            msgOffline.setNmoState((byte) 3);//离线
            int ref=NormalMessageOfflineService.addNomalMessage(msgOffline);
            if(ref<=0){
                ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
                errorMessagePacket.setSuccess(false);
                errorMessagePacket.setErrorCode(PacketsCommandMapping.MESSAGE_REQUEST);
                errorMessagePacket.setReason("服务器忙或者服务器数据库异常,对方不在线并且离线消息同步失败");
                fromChannel.writeAndFlush(errorMessagePacket);
                System.out.println("离线消息服务器信息添加失败");
            }else{
                System.out.println("离线消息服务器信息添加成功，msgOffline.getid="+msgOffline.getId());

                System.out.print("发送到原发送者在线客户端  ");
                fromChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
                    if(future.isSuccess()){
                        System.out.println("发送成功");
                    }else{
                        System.out.println("发送失败");
                    }
                });
            }
//            msgOffline.set
//            NormalMessageOfflineService.addNomalMessage()
        }
    }


}