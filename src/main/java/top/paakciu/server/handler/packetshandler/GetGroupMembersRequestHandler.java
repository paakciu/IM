package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.packet.GetGroupMembersRequestPacket;
import top.paakciu.protocal.packet.GetGroupMembersResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ChannelUser;
import top.paakciu.utils.GroupsHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetGroupMembersRequestHandler
 * @date: 2021/4/3 16:00
 */
@ChannelHandler.Sharable
public class GetGroupMembersRequestHandler extends SimpleChannelInboundHandler<GetGroupMembersRequestPacket> {
    public static final GetGroupMembersRequestHandler INSTANCE = new GetGroupMembersRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GetGroupMembersRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            Long groupId= msg.getGroupId();
            ChannelGroup channelGroup = GroupsHelper.getChannelGroup(groupId);

            List<ChannelUser> list=new ArrayList<>();
            for (Channel channel : channelGroup) {
                ChannelUser user= AttributesHelper.getChannelUser(channel);
                list.add(user);
            }

            GetGroupMembersResponsePacket getGroupMembersResponsePacket=new GetGroupMembersResponsePacket();
            getGroupMembersResponsePacket.setGroupId(groupId);
            getGroupMembersResponsePacket.setChannelUserList(list);

            ctx.writeAndFlush(getGroupMembersResponsePacket);
        });
    }
}
