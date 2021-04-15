package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.GroupMsg;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.GroupMessageRequestPacket;
import top.paakciu.protocal.packet.GroupMessageResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMembersService;
import top.paakciu.service.GroupMessageOfflineService;
import top.paakciu.service.GroupMessageService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;
import top.paakciu.utils.GroupsHelper;

import java.util.Date;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GroupMessageRequestHandler
 * @date: 2021/4/3 16:48
 */
//TODO 根据ipad图重做该处理
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        NettyServer.executor.submit(()->{
            // 拿到 groupId 构造群聊消息的响应
            Long groupId = requestPacket.getToGroupId();
            ChannelUser fromChannelUser = AttributesHelper.getChannelUser(ctx.channel());

            Date uniDate=new Date();

            // 拿到群聊对应的 channelGroup，写到每个客户端
            ChannelGroup channelGroup = GroupsHelper.getChannelGroup(groupId);

            GroupInfo info=null;
            List<GroupMembers> list=null;
            if(channelGroup==null){
                //如果群组为空，检查数据库是否有需要从数据库中添加缓存
                info= GroupInfoService.getGroupInfoById(groupId);
                if(info!=null){
                    //就是说有这个群组，这个list是所有人，直接获取群组--在线人
                    list= GroupMembersService.getGroupMembersByGroupId(groupId);
                    channelGroup=new DefaultChannelGroup(ctx.executor());
                    for (GroupMembers members : list) {
                        Long tempuserId=members.getGroupUserid();
                        Channel channel= AttributesHelper.getChannel(tempuserId);
                        if(channel!=null)
                            channelGroup.add(channel);
                    }
                    //修复群组确实的群
                    GroupsHelper.addGroup(groupId,channelGroup);
                }else{
                    handleError(ctx.channel(),"所请求的群聊不存在");
                    return ;
                }
                //再次获取
                channelGroup = GroupsHelper.getChannelGroup(groupId);
            }


            if(channelGroup!=null){

                if(info==null) {
                    info= GroupInfoService.getGroupInfoById(groupId);
                }
                if(info!=null&&list==null){
                    //就是说有这个群组，这个list是所有人,包括不在线的
                    list = GroupMembersService.getGroupMembersByGroupId(info.getId());
                }
                if(list==null){
                    //这里必是出问题了的，数据库都没找到的群组，channelGroup却不为空
                    handleError(ctx.channel(),"出现意外的错误");
                    return;
                }

                GroupMsg groupMsg=new GroupMsg();
                groupMsg.setGroupid(groupId);
                groupMsg.setFromid(fromChannelUser.getUserId());
                groupMsg.setTime(uniDate);
                groupMsg.setMsg(requestPacket.getMessage());

                //同步持久化群组消息
                Integer ref=GroupMessageService.addGroupMessage(groupMsg);
                if(ref==null||ref==0){
                    //持久化失败
                    handleError(ctx.channel(),"持久化消息失败");
                    return;
                }
                //同步持久化所有没有搜到消息的人的消息
                for (GroupMembers members : list) {
                    Long userIdtemp=members.getGroupUserid();
                    Channel userChanneltemp= AttributesHelper.getChannel(userIdtemp);
                    if(userChanneltemp==null){
                        //持久化没在群中的人
                        Integer addref=GroupMessageOfflineService.addOnlyGroupOfflineMessage(groupId,userIdtemp,groupMsg.getId());
                        if(addref==null){
                            //todo 持久化失败的，不需要停止
                            //handleError(ctx.channel(),"部分离线用户离线消息失败");
                        }
                    }
                }

                //通知所有在线的人，并且添加群组信息记录
                GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
                responsePacket.setFromUserId(fromChannelUser.getUserId());
                responsePacket.setFromUserName(fromChannelUser.getUserName());
                responsePacket.setToGroupId(groupId);
                responsePacket.setDate(uniDate);
                responsePacket.setMessageId(groupMsg.getId());
                responsePacket.setMessage(requestPacket.getMessage());
                channelGroup.writeAndFlush(responsePacket);
            }



        });
    }

    public void handleError(Channel channel, String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.GROUP_MESSAGE_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
