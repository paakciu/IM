package top.paakciu.client.handler;

import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.ListenWith2Funciton;
import top.paakciu.client.listener.ListenWith4Function;

/**
 * @author paakciu
 * @ClassName: SimpleChannelInboundHandlerWith2Function
 * @date: 2021/4/12 14:35
 */
public abstract class SimpleChannelInboundHandlerWith2Function<I> extends SimpleChannelInboundHandler<I> {
    protected ListenWith2Funciton listeners=new ListenWith2Funciton();
    public ListenWith2Funciton getListeners() {
        return listeners;
    }
}
