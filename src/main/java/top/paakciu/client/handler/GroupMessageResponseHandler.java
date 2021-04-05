package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.protocal.packet.GroupMessageResponsePacket;

/**
 * @author paakciu
 * @ClassName: GroupMessageResponseHandler
 * @date: 2021/4/3 17:03
 */

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    public final static GroupMessageResponseHandler INSTANCE=new GroupMessageResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        System.out.println("收到群"+msg.getToGroupId()+" "+msg.getFromUserId()+"发出的消息："+msg.getMessage());
    }

}
