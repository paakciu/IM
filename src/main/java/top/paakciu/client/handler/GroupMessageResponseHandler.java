package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.GroupMessageResponsePacket;

/**
 * @author paakciu
 * @ClassName: GroupMessageResponseHandler
 * @date: 2021/4/3 17:03
 */
//TODO
public class GroupMessageResponseHandler extends SimpleChannelInboundHandlerWithHandlerFunction<GroupMessageResponsePacket> {
    public final static GroupMessageResponseHandler INSTANCE=new GroupMessageResponseHandler();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        if(listeners!=null&&listeners.handlerListener!=null)
            listeners.handlerListener.onHandle(msg);
    }

}
