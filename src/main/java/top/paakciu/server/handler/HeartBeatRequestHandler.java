package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.HeartBeatPacket;

/**
 * @author paakciu
 * @ClassName: HeartBeatRequestHandler
 * @date: 2021/3/31 19:05
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatPacket> {
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatPacket requestPacket) {
        //收到直接返回心跳包就行！
        ctx.writeAndFlush(new HeartBeatPacket());
    }
}