package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import top.paakciu.config.IMConfig;
import top.paakciu.utils.AttributesHelper;

import java.util.concurrent.TimeUnit;

/**
 * @author paakciu
 * @ClassName: ClientIdleDetectionHandler
 * @date: 2021/3/10 17:08
 */
public class ClientIdleDetectionHandler extends IdleStateHandler {

    //空闲间隔时间
    private static final long IDLE_TIME = IMConfig.CLIENT_IDLE_TIME;

    public ClientIdleDetectionHandler() {
        super(0, 0, IDLE_TIME, IMConfig.CLIENT_TIME_UNIT);
    }
    public ClientIdleDetectionHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit) {
        super(readerIdleTime, writerIdleTime, allIdleTime, unit);
    }


    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(IDLE_TIME + "秒内未读到数据，关闭连接");
        Long id=AttributesHelper.getChannelUser(ctx.channel()).getUserId();
        System.out.println("结束用户:"+id);
        AttributesHelper.logout(ctx.channel());
        ctx.channel().close();
    }
}
