package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.ExtraGroupRequestPacket;
import top.paakciu.protocal.packet.ExtraGroupResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;
import top.paakciu.utils.GroupsHelper;

import java.util.Date;

/**
 * @author paakciu
 * @ClassName: ExtraGroupRequestHandler
 * @date: 2021/4/15 17:09
 */
@ChannelHandler.Sharable
public class ExtraGroupRequestHandler extends SimpleChannelInboundHandler<ExtraGroupRequestPacket>{
    public static final ExtraGroupRequestHandler INSTANCE=new ExtraGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExtraGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()-> {
            //拿到发送方的信息
            ChannelUser channelUser = AttributesHelper.getChannelUser(ctx.channel());
            //拿到在线群
            ChannelGroup channelGroup= GroupsHelper.getChannelGroup(msg.getGroupId());
            System.out.println("收到拓展消息！");
            if(channelGroup==null){
                System.out.println("拓展消息错误！");
                handleError(ctx.channel(),"在线群组不存在,或者群中没人在线");
            }else{

                ExtraGroupResponsePacket responsePacket=new ExtraGroupResponsePacket();
                responsePacket.setDate(new Date());
                responsePacket.setFromUserId(channelUser.getUserId());
                responsePacket.setFromUserName(channelUser.getUserName());
                responsePacket.setLen(msg.getLen());
                responsePacket.setType(msg.getType());
                responsePacket.setMessage(msg.getMessage());
                responsePacket.setToGroupId(msg.getGroupId());

                channelGroup.writeAndFlush(responsePacket);
                System.out.println("拓展消息发送完毕！");
            }

        });
    }
    public void handleError(Channel channel,String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.EXTRA_GROUP_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
