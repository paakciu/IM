package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.QuitGroupRequestPacket;
import top.paakciu.protocal.packet.QuitGroupResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupMembersService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.GroupsHelper;

/**
 * @author paakciu
 * @ClassName: QuitGroupRequestHandler
 * @date: 2021/4/3 15:27
 */
//TODO 根据ipad图重做该处理
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            Long groupId=msg.getGroupId();
            Long userId=msg.getUserId();

            ChannelGroup channelGroup= GroupsHelper.getChannelGroup(groupId);
            //持久化删除是不管他是否在在线群组里的
            int ref=GroupMembersService.deleteGroupMembersByGroupIdAndUserId(groupId,userId);
            if (ref==0){
                handleError(ctx.channel(),"删除群组成员失败");
                return;
            }
            if(channelGroup!=null) {
                System.out.println("channelGroup不为空，回复删除");
                //回复
                QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
                responsePacket.setGroupId(msg.getGroupId());
                responsePacket.setUserId(msg.getUserId());
                responsePacket.setSuccess(true);
                //通知所有人
                channelGroup.writeAndFlush(responsePacket);

                AttributesHelper.removeChannelGroup(ctx.channel(),groupId,channelGroup);
                channelGroup.remove(groupId);
                if(channelGroup.size()==0){
                    //已经为空了，就不要维护这个东西了
                    GroupsHelper.removeGroup(groupId,channelGroup);
                }
            }else {
                System.out.println("channelGroup为空,发送点错误");
            }

        });
    }

    public void handleError(Channel channel,String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.QUIT_GROUP_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
