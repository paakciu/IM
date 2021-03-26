package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.MessageResponsePacket;


import java.util.Date;

/**
 * @author paakciu
 * @ClassName: MessageRequestHandler
 * @date: 2021/3/3 16:53
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    public final static MessageResponseHandler INSTANCE=new MessageResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        //System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
    }
}
