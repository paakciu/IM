package top.paakciu.protocal.codec.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import top.paakciu.protocal.codec.PacketCodec;


import java.util.List;

/**
 * @author paakciu
 * @ClassName: PacketDecoder
 * @date: 2021/3/3 17:39
 */
@ChannelHandler.Sharable
public class PacketDecoder extends ByteToMessageDecoder {

    //单例
    public static final PacketDecoder INSTANCE = new PacketDecoder();
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.decode(in));
    }
}
