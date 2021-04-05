package top.paakciu.server.handler.packetshandler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.packet.GroupMessageRequestPacket;
import top.paakciu.protocal.packet.GroupMessageResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ChannelUser;
import top.paakciu.utils.GroupsHelper;

/**
 * @author paakciu
 * @ClassName: GroupMessageRequestHandler
 * @date: 2021/4/3 16:48
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        NettyServer.executor.submit(()->{
            // 拿到 groupId 构造群聊消息的响应
            Long groupId = requestPacket.getToGroupId();
            ChannelUser user=AttributesHelper.getChannelUser(ctx.channel());

            GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
            responsePacket.setFromUserId(user.getUserId());
            responsePacket.setToGroupId(groupId);
            responsePacket.setMessage(requestPacket.getMessage());


            // 拿到群聊对应的 channelGroup，写到每个客户端
            ChannelGroup channelGroup = GroupsHelper.getChannelGroup(groupId);
            channelGroup.writeAndFlush(responsePacket);
        });
    }
}
