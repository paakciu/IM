package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.paakciu.config.IMConfig;
import top.paakciu.protocal.packet.HeartBeatPacket;
import top.paakciu.utils.AttributesHelper;

import java.util.concurrent.TimeUnit;

/**
 * @author paakciu
 * @ClassName: HeartBeatTimerHandler
 * @date: 2021/3/31 18:55
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    public final static HeartBeatTimerHandler INSTANCE=new HeartBeatTimerHandler();
    private static final int HEARTBEAT_INTERVAL = IMConfig.CLIENT_HEARTBEAT_INTERVAL;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //if (AttributesHelper.hasLogin(ctx.channel()))
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }

}
