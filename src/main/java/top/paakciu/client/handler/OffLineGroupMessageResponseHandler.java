package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.protocal.packet.OffLineGroupMessageResponsePacket;

/**
 * @author paakciu
 * @ClassName: OffLineGroupMessageResponseHandler
 * @date: 2021/4/15 15:54
 */
public class OffLineGroupMessageResponseHandler extends SimpleChannelInboundHandlerWith4Function<OffLineGroupMessageResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OffLineGroupMessageResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            if(listeners!=null&&listeners.successListener!=null){
                listeners.successListener.onSuccess(msg);
            }
        }else{
            if(listeners!=null&&listeners.failListener!=null){
                listeners.failListener.onFail(msg);
            }
        }
    }
}
