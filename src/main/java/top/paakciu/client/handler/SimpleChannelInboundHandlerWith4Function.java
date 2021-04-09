package top.paakciu.client.handler;

import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.ListenWith4Function;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;

public abstract class SimpleChannelInboundHandlerWith4Function<I> extends SimpleChannelInboundHandler<I> {
    protected ListenWith4Function<I,I> listeners=new ListenWith4Function();
    public ListenWith4Function<I, I> getListeners() {
        return listeners;
    }


}
