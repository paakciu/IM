package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;

/**
 * @author paakciu
 * @ClassName: CreateGroupResponseHandler
 * @date: 2021/4/2 16:58
 */

public class CreateGroupResponseHandler extends SimpleChannelInboundHandlerWith4Function<CreateGroupResponsePacket> {
    public final static CreateGroupResponseHandler INSTANCE=new CreateGroupResponseHandler();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        if(createGroupResponsePacket.isSuccess()) {
            if(listeners.successListener!=null)
                listeners.successListener.onSuccess(createGroupResponsePacket);
        }else{
            if(listeners.failListener!=null)
                listeners.failListener.onFail(createGroupResponsePacket);
        }
    }
}
