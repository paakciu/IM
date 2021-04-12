package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.MessageResponsePacket;
import top.paakciu.protocal.packet.PullMessageRequestPacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.NormalMessageService;
import top.paakciu.service.UserService;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: PullMessageRequestHandler
 * @date: 2021/4/11 22:16
 */
@ChannelHandler.Sharable
public class PullMessageRequestHandler extends SimpleChannelInboundHandler<PullMessageRequestPacket> {
    public static final PullMessageRequestHandler INSTANCE=new PullMessageRequestHandler();

    //            private boolean isGroup=false;
//            private Long fromId;//
//            private Long id1;
//            private Long id2;
//            private Long fromMessageId=-1L;//默认
//            private boolean getBigger=true;//根据消息ID往下还是往上数消息数
//            private Integer nums=-1;//拉取多少个

    //这是根据消息的id，向上或者向下获取所有消息
    //public void setAsGroupByFromMessageId(Long fromMessageId1,boolean Bigger)
    //这是根据size 从最新的消息获取特定数量
    //public void setAsGroupBySize(int size)
    //这是根据消息的id，向上或者向下获取 特定条数的消息
    //public void setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size)

    //public void setAsSingleByFromMessageId(Long Id1,Long Id2,boolean Bigger)
    //public void setAsSingleBySize(int size)
    //public void setAsSingleByFromMessageIdAndSize(Long Id1,Long Id2,boolean Bigger,int Size)

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PullMessageRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{

            System.out.println("收到拉取历史消息请求"+msg);
            if(msg.isGroup()){
                //群聊的处理
                if(msg.getMods()==1){
                    //todo
                    //这是根据消息的id，向上或者向下获取所有消息-setAsGroupByFromMessageId(Long fromMessageId1,boolean Bigger)

                }else if (msg.getMods()==2){
                    //todo
                    //这是根据size 从最新的消息获取特定数量-setAsGroupBySize(int size)

                }else if (msg.getMods()==3){
                    //todo
                    //这是根据消息的id，向上或者向下获取 特定条数的消息-setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size)

                }else{
                    // error
                    ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
                    errorMessagePacket.setSuccess(false);
                    errorMessagePacket.setErrorCode(PacketsCommandMapping.PULL_MESSAGE_REQUEST);
                    errorMessagePacket.setReason("意外的控制参数");
                    ctx.channel().writeAndFlush(errorMessagePacket);
                }
            }else {
                //单聊的处理
                System.out.println("拉取单聊消息，mods="+msg.getMods());
                if(msg.getMods()==1){
                    //这是根据消息的id，向上或者向下获取所有消息-setAsSingleByFromMessageId(Long id,Long Id1,Long Id2,boolean Bigger)
                    System.out.println("处理方法setAsSingleByFromMessageId");
                    List<NormalMsg> list=NormalMessageService.getMsgByFromMessageId(msg.getFromMessageId(),msg.getId1(), msg.getId2(), msg.isGetBigger());
                    sendSingle(ctx.channel(),list);

                }else if (msg.getMods()==2){
                    //todo
                    //这是根据size 从最新的消息获取特定数量-setAsSingleBySize(int size)
                    List<NormalMsg> list=NormalMessageService.getMsgBySize(msg.getId1(),msg.getId2(),msg.getNums());
                    sendSingle(ctx.channel(),list);

                }else if (msg.getMods()==3){
                    //todo
                    //这是根据消息的id，向上或者向下获取 特定条数的消息
                    //setAsSingleByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int Size)
                    List<NormalMsg> list=NormalMessageService.getMsgByFromMessageIdAndSize(
                            msg.getFromMessageId(),msg.getId1(), msg.getId2(), msg.isGetBigger(),msg.getNums());
                    sendSingle(ctx.channel(),list);

                }else{
                    // error
                    ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
                    errorMessagePacket.setSuccess(false);
                    errorMessagePacket.setErrorCode(PacketsCommandMapping.PULL_MESSAGE_REQUEST);
                    errorMessagePacket.setReason("意外的控制参数");
                    ctx.channel().writeAndFlush(errorMessagePacket);
                }
            }

        });
    }
    public void sendSingle(Channel channel,List<NormalMsg> list)
    {
        for (NormalMsg normalMsg : list) {
            MessageResponsePacket messageResponsePacket=new MessageResponsePacket();
            messageResponsePacket.setMessageId(normalMsg.getId());
            messageResponsePacket.setMessage(normalMsg.getNmMsg());
            messageResponsePacket.setDate(normalMsg.getNmTime());
            messageResponsePacket.setFromUserId(normalMsg.getNmFromid());
            User user=UserService.getUserById(normalMsg.getNmFromid());
            if(user!=null)
                messageResponsePacket.setFromUserName(user.getUsername());
            messageResponsePacket.setToUserId(normalMsg.getNmToid());

            channel.writeAndFlush(messageResponsePacket);
        }
    }
}
