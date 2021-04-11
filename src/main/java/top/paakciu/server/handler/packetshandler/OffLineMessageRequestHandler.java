package top.paakciu.server.handler.packetshandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.packet.MessageResponsePacket;
import top.paakciu.protocal.packet.OffLineMessageRequestPacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.NormalMessageOfflineService;
import top.paakciu.service.UserService;
import top.paakciu.utils.Sqlutils;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: OffLineMessageRequest
 * @date: 2021/4/11 20:34
 */
@ChannelHandler.Sharable
public class OffLineMessageRequestHandler extends SimpleChannelInboundHandler<OffLineMessageRequestPacket> {
    public static final OffLineMessageRequestHandler INSTANCE = new OffLineMessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffLineMessageRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            List<NormalMsg> list=NormalMessageOfflineService.getNormalMsgOfflineByid(msg.getId());
            for (NormalMsg normalMsg : list) {
                MessageResponsePacket messageResponsePacket=new MessageResponsePacket();
                messageResponsePacket.setMessageId(normalMsg.getId());
                messageResponsePacket.setToUserId(normalMsg.getNmToid());
                messageResponsePacket.setMessage(normalMsg.getNmMsg());
                messageResponsePacket.setFromUserId(normalMsg.getNmFromid());

                User user=UserService.getUserById(normalMsg.getNmFromid());
                messageResponsePacket.setFromUserName(user.getUsername());
                messageResponsePacket.setDate(normalMsg.getNmTime());

                ctx.writeAndFlush(messageResponsePacket);
            }
        });
    }
}
