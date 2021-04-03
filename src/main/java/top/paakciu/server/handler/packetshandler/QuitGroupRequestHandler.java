package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.packet.QuitGroupRequestPacket;
import top.paakciu.protocal.packet.QuitGroupResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.GroupsHelper;

/**
 * @author paakciu
 * @ClassName: QuitGroupRequestHandler
 * @date: 2021/4/3 15:27
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            Long groupId=msg.getGroupId();
            ChannelGroup channelGroup= GroupsHelper.getChannelGroup(groupId);
            if(channelGroup!=null) {
                channelGroup.remove(groupId);

                //回复
                QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
                responsePacket.setGroupId(msg.getGroupId());
                responsePacket.setSuccess(true);
                //
                ctx.writeAndFlush(responsePacket);
            }

        });
    }
}
