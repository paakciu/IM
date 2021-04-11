package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.packet.JoinGroupRequestPacket;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.GroupsHelper;

/**
 * @author paakciu
 * @ClassName: JoinGroupRequestHandler
 * @date: 2021/4/3 14:34
 */
//TODO 根据ipad图重做该处理
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();
    @Override

    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            //获取对应的群组，然后加入聊天组
            Long groupId=msg.getGroupId();
            ChannelGroup group= GroupsHelper.getChannelGroup(groupId);
            if(group!=null) {
                //TODO 这里出了问题，应该是在数据库检测是否在群组里，再加入，逻辑错了


                Channel channel=AttributesHelper.getChannel(msg.getUserId());
                if(channel==null)
                {
                    JoinGroupResponsePacket joinGroupResponsePacket=new JoinGroupResponsePacket();
                    joinGroupResponsePacket.setSuccess(false);
                    joinGroupResponsePacket.setReason("输入UserId错误");
                    ctx.writeAndFlush(joinGroupResponsePacket);
                    return ;
                }
                group.add(channel);

                //回复
                JoinGroupResponsePacket joinGroupResponsePacket=new JoinGroupResponsePacket();
                joinGroupResponsePacket.setSuccess(true);
                joinGroupResponsePacket.setGroupId(groupId);
                //快速跳过
                ctx.writeAndFlush(joinGroupResponsePacket);
            }
        });
    }
}
