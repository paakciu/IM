package top.paakciu.protocal.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.BasePacket;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: M2MPacketCodecHandler
 * @date: 2021/3/10 13:45
 */
@ChannelHandler.Sharable
public class M2MPacketCodecHandler extends MessageToMessageCodec<ByteBuf, BasePacket> {

    //单例
    public static final M2MPacketCodecHandler INSTANCE = new M2MPacketCodecHandler();

    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, List<Object> out) throws Exception {
        //PacketCodec.encode(out,msg);
        //这个生成buffer让我感觉很不舒服
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.encode(byteBuf,msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodec.decode(msg));
    }
}
