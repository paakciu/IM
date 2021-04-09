package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.ErrorListener;
import top.paakciu.protocal.packet.ErrorMessagePacket;


/**
 * @author paakciu
 * @ClassName: ErrorMessageHandler
 * @date: 2021/3/31 14:33
 */

public class ErrorMessageHandler extends SimpleChannelInboundHandler<ErrorMessagePacket> {
    public final static ErrorMessageHandler INSTANCE=new ErrorMessageHandler();

    private ErrorListener<ErrorMessagePacket> listener;
    public void setListener(ErrorListener<ErrorMessagePacket> listener) {
        this.listener = listener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ErrorMessagePacket msg) throws Exception {
        if(listener!=null)
            listener.onError(msg);
    }
}
