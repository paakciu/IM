package top.paakciu.server.handler.packetshandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.codec.PacketCodec;

import top.paakciu.protocal.packet.MessageRequestPacket;
import top.paakciu.protocal.packet.MessageResponsePacket;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.Session;

import java.util.Date;


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
        //System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        Session session= AttributesHelper.getSession(ctx.channel());

        //转发客户端消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        //messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        //ByteBuf responseByteBuf = PacketCodec.encode(ctx.alloc().buffer(), messageResponsePacket);

        //拿到消息接收方的 channel
        Channel toChannel = AttributesHelper.getChannel(messageRequestPacket.getToUserId());
        //System.out.println("从客户端"+session.getUserId()+"到客户端"+messageRequestPacket.getToUserId());
        //System.out.println("频道hashcode:"+ctx.channel()+">"+toChannel);

        if (toChannel != null && AttributesHelper.hasLogin(toChannel)) {
            toChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
        //回复from客户端
        //ctx.channel().writeAndFlush(messageResponsePacket);
    }
}