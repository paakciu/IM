package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.protocal.packet.ExtraResponsePacket;
import top.paakciu.utils.ExtraPacketHelper;

/**
 * @author paakciu
 * @ClassName: ExtraResponseHandler
 * @date: 2021/4/12 21:07
 */
public class ExtraResponseHandler extends SimpleChannelInboundHandlerWithHandlerFunction<ExtraResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ExtraResponsePacket msg) throws Exception {
        if(listeners.handlerListener!=null){
            listeners.handlerListener.onHandle(msg);
        }
    }
}
