package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.JoinGroupRequestPacket;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMembersService;
import top.paakciu.service.UserService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.GroupsHelper;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: JoinGroupRequestHandler
 * @date: 2021/4/3 14:34
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            //获取对应的群组，然后加入聊天组
            Long groupId=msg.getGroupId();
            Long userId = msg.getUserId();
            JoinGroupResponsePacket joinGroupResponsePacket=new JoinGroupResponsePacket();
            //region 检查用户是否存在并取到数据
            User user=UserService.getUserById(msg.getUserId());
            if(user==null){
                handleError(ctx.channel(),"用户id错误");
                return;
            }
            joinGroupResponsePacket.setUserName(user.getUsername());
            //endregion
            //region 获取在线群组，查看数据库
            //获取在线群组
            ChannelGroup group= GroupsHelper.getChannelGroup(groupId);
            if(group==null){
                //如果群组为空，检查数据库是否有需要从数据库中添加缓存
                GroupInfo info=GroupInfoService.getGroupInfoById(groupId);
                if(info!=null){
                    //就是说有这个群组，直接获取群组--在线人
                    List<GroupMembers> list= GroupMembersService.getGroupMembersByGroupId(groupId);
                    group=new DefaultChannelGroup(ctx.executor());
                    for (GroupMembers members : list) {
                        Long tempuserId=members.getGroupUserid();
                        Channel channel= AttributesHelper.getChannel(tempuserId);
                        if(channel!=null)
                            group.add(channel);
                    }
                    //修复群组确实的群
                    GroupsHelper.addGroup(groupId,group);
                }else{
                    handleError(ctx.channel(),"没有该群");
                    //return ;
                }
            }
            //endregion

            //region 再次检测是否有在线群组
            if(group!=null) {
                //这里在数据库检测是否在群组里，再加入
                //检查是否已经在群中
                List<GroupMembers> list=GroupMembersService.getGroupMembersByGroupIdAndUserId(groupId,userId);
                int listsize=list.size();
                if(listsize<1){
                    //region 不在群里--涉及到持久化
                    int ref=GroupMembersService.addGroupMember(groupId,userId);
                    if(ref==1){
                        //持久化成功
                        //判断是否在线并且添加到群组中
                        Channel channel=AttributesHelper.getChannel(msg.getUserId());
                        if(channel==null)
                        {
                            //region 新加入数据库，但是没在线
                            //TODO 情况1-新加入数据库，但是没在线
                            joinGroupResponsePacket.setUserId(userId);
                            joinGroupResponsePacket.setGroupId(groupId);
                            joinGroupResponsePacket.setSuccess(true);
                            joinGroupResponsePacket.setOnLine(false);
                            joinGroupResponsePacket.setNewJoin(true);
                            //加入到群聊成功
                            group.writeAndFlush(joinGroupResponsePacket);
                            return ;
                            //endregion
                        }else{
                            //region 新加入数据库，在线，直接加入群组
                            //TODO 情况2-新加入数据库，在线，直接加入群组
                            joinGroupResponsePacket.setUserId(userId);
                            joinGroupResponsePacket.setGroupId(groupId);
                            joinGroupResponsePacket.setSuccess(true);
                            joinGroupResponsePacket.setOnLine(true);
                            joinGroupResponsePacket.setNewJoin(true);
                            //加入到群聊成功
                            GroupsHelper.getChannelGroup(groupId).add(channel);
                            AttributesHelper.addChannelGroup(channel,groupId,group);
                            group.writeAndFlush(joinGroupResponsePacket);
                            return ;
                            //endregion
                        }
                    }else{
                        handleError(ctx.channel(),"持久化加入该成员失败");
                    }
                    //endregion
                }else if(list.size()==1){
                    //region //在群的数据库里了已经，但是这步操作是添加到在线名单去，不涉及持久化
                    //                    //判断是否在线并且添加到群组中
                    Channel channel=AttributesHelper.getChannel(msg.getUserId());
                    if(channel!=null){
                        if(group.contains(channel)){
                            //已经在数据库中，又在线，又已经在group中了，就不用再加入了
                            //TODO 情况3-已经在数据库中，又在线，又已经在group中了，就不用再加入了
                            joinGroupResponsePacket.setUserId(userId);
                            joinGroupResponsePacket.setGroupId(groupId);
                            joinGroupResponsePacket.setSuccess(false);
                            joinGroupResponsePacket.setOnLine(true);
                            joinGroupResponsePacket.setNewJoin(false);
                            joinGroupResponsePacket.setReason("已经在数据库中，又在线，又已经在group中了，就不用再加入了");
                            ctx.writeAndFlush(joinGroupResponsePacket);
                            return ;
                        }
                        //region 不是新加入数据库的，在线，直接加入群组
                        //TODO 情况4-不是新加入数据库的，在线，直接加入群组
                        joinGroupResponsePacket.setUserId(userId);
                        joinGroupResponsePacket.setGroupId(groupId);
                        joinGroupResponsePacket.setSuccess(true);
                        joinGroupResponsePacket.setOnLine(true);
                        joinGroupResponsePacket.setNewJoin(false);
                        //加入到群聊成功
                        GroupsHelper.getChannelGroup(groupId).add(channel);
                        AttributesHelper.addChannelGroup(channel,groupId,group);
                        group.writeAndFlush(joinGroupResponsePacket);
                        return ;
                        //endregion
                    }else{
                        //已经在数据库中，但是又不在线，就是不需要操作的
                        handleError(ctx.channel(),"已经在数据库中，但是又不在线，就是不需要操作的");
                    }
                    //endregion
                }else if(listsize>1){
                    //出现意外情况
                    handleError(ctx.channel(),"出现意外情况，群组里有两个相同用户");
                }

            }
            //endregion
        });
    }


    public void handleError(Channel channel,String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.JOIN_GROUP_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
