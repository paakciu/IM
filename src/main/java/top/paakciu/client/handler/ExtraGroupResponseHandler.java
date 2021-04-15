package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.protocal.packet.ExtraGroupResponsePacket;

/**
 * @author paakciu
 * @ClassName: ExtraGroupResponseHandler
 * @date: 2021/4/15 16:57
 */
public class ExtraGroupResponseHandler extends SimpleChannelInboundHandlerWithHandlerFunction<ExtraGroupResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExtraGroupResponsePacket msg) throws Exception {
        if(listeners!=null&&listeners.handlerListener!=null){
            listeners.handlerListener.onHandle(msg);
        }
    }
}
