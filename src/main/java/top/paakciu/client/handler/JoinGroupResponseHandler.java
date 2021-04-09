package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.listener.ListenWith4Function;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;

/**
 * @author paakciu
 * @ClassName: JoinGroupResponseHandler
 * @date: 2021/4/3 15:23
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandlerWith4Function<JoinGroupResponsePacket> {
    public final static JoinGroupResponseHandler INSTANCE=new JoinGroupResponseHandler();

//    private ListenWith4Function<JoinGroupResponsePacket,JoinGroupResponsePacket> listeners=new ListenWith4Function();
//    public ListenWith4Function<JoinGroupResponsePacket, JoinGroupResponsePacket> getListeners() {
//        return listeners;
//    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            if(listeners.successListener!=null)
                listeners.successListener.onSuccess(msg);
        } else {
            if(listeners.failListener!=null)
                listeners.failListener.onFail(msg);
        }
    }
}
