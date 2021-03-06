package top.paakciu.protocal.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.BasePacket;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: CodecHandler
 * @date: 2021/3/3 17:50
 */
@Deprecated
public class CodecHandler extends ByteToMessageCodec<BasePacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, ByteBuf out) throws Exception {
        PacketCodec.encode(out,msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.decode(in));
    }
}
