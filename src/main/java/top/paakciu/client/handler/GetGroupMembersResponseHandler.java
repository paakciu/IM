package top.paakciu.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.paakciu.client.NettyClient;
import top.paakciu.protocal.packet.GetGroupMembersResponsePacket;

/**
 * @author paakciu
 * @ClassName: GetGroupMembersResponseHandler
 * @date: 2021/4/3 16:05
 */

public class GetGroupMembersResponseHandler extends SimpleChannelInboundHandler<GetGroupMembersResponsePacket> {
    public final static GetGroupMembersResponseHandler INSTANCE=new GetGroupMembersResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetGroupMembersResponsePacket msg) throws Exception {

        System.out.println("群[" + msg.getGroupId() + "]中的人包括：" + msg.getChannelUserList());
    }
}
