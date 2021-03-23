package top.paakciu.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author paakciu
 * @ClassName: IdleDetectionHandler
 * @date: 2021/3/10 17:08
 */
public class IdleDetectionHandler extends IdleStateHandler {

    //空闲间隔时间
    private static final long IDLE_TIME = 15;

    public IdleDetectionHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit) {
        super(readerIdleTime, writerIdleTime, allIdleTime, unit);
    }
    public IdleDetectionHandler() {
        super(0, 0, IDLE_TIME, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
