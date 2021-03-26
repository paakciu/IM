package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.PaakciuFuture;
import top.paakciu.client.ResponseListener;
import top.paakciu.protocal.packet.RegisterResponsePacket;

/**
 * @author paakciu
 * @ClassName: RegisterResponseHandler
 * @date: 2021/3/26 0:09
 */
public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {
    public final static RegisterResponseHandler INSTANCE=new RegisterResponseHandler();
    private PaakciuFuture future;

    public PaakciuFuture getFuture() {
        return future;
    }

    public void setFuture(PaakciuFuture future) {
        this.future = future;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket msg) throws Exception {
        if(msg.isSuccess()) {
            if(future!=null&&future.getListener()!=null)

                future.getListener().onSuccess(msg.getMsg());
        }else{
            if(future!=null&&future.getListener()!=null)
                future.getListener().onFail(msg.getMsg());
        }
    }
}
