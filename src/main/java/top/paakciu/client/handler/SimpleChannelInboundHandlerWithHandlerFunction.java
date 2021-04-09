package top.paakciu.client.handler;

import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.*;
import top.paakciu.protocal.packet.MessageResponsePacket;

public abstract class SimpleChannelInboundHandlerWithHandlerFunction<I> extends SimpleChannelInboundHandler<I> {
    protected ListenWithHandlerFunction<I> listeners=new ListenWithHandlerFunction();
    public ListenWithHandlerFunction<I> getListeners() {
        return listeners;
    }

}
