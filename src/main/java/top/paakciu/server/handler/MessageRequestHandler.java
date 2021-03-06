package top.paakciu.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.MessagePacket;

import java.util.Date;


/**
 * @author paakciu
 * @ClassName: MessageRequestHandler
 * @date: 2021/3/3 18:09
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messageRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

        MessagePacket messageResponsePacket = new MessagePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        //ByteBuf responseByteBuf = PacketCodec.encode(ctx.alloc().buffer(), messageResponsePacket);
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}