package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.protocal.packet.GetGroupListResponsePacket;

/**
 * @author paakciu
 * @ClassName: GetGroupListResponseHandler
 * @date: 2021/4/15 12:27
 */
public class GetGroupListResponseHandler extends SimpleChannelInboundHandlerWith4Function<GetGroupListResponsePacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetGroupListResponsePacket msg) throws Exception {
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
