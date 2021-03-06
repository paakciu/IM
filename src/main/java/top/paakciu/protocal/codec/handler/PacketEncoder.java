package top.paakciu.protocal.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.paakciu.protocal.codec.PacketCodec;
import top.paakciu.protocal.packet.BasePacket;

/**
 * @author paakciu
 * @ClassName: PacketEncoder
 * @date: 2021/3/3 17:37
 */
public class PacketEncoder extends MessageToByteEncoder<BasePacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, ByteBuf out) throws Exception {
        PacketCodec.encode(out,msg);
    }
}
