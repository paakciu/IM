package top.paakciu.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import top.paakciu.utils.AttributesHelper;

/**
 * @author paakciu
 * @ClassName: AuthorityHandler
 * @date: 2021/3/5 14:11
 */
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class AuthorityHandler extends ChannelInboundHandlerAdapter {

    //单例
    public static final AuthorityHandler INSTANCE = new AuthorityHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(AttributesHelper.hasLogin(ctx.channel())){
            //如果已经登录成功了
            //System.out.println("channelRead登录成功");
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }else{
            //如果没有登录，就不能后续处理，要关闭连接
            //System.out.println("channelRead登录失败");
            System.out.println("收到消息，但是没有登录，拒绝下一步进行");
            ctx.channel().close();
        }

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(AttributesHelper.hasLogin(ctx.channel())){
            //System.out.println("当前连接登录验证完毕，后续的请求无需验证, 该AuthorityHandler被移除");
        } else {
            //System.out.println("当前连接登录验证失败，强制关闭连接!");
        }
//        super.handlerRemoved(ctx);
    }
}
