package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.packet.GetGroupMembersRequestPacket;
import top.paakciu.protocal.packet.GetGroupMembersResponsePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMembersService;
import top.paakciu.service.UserService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;
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
            System.out.println("接收到群成员请求的id是"+groupId);
            ChannelGroup channelGroup = GroupsHelper.getChannelGroup(groupId);
            List<ChannelUser> list=new ArrayList<>();
            if(channelGroup!=null){
                for (Channel channel : channelGroup) {
                    ChannelUser user= AttributesHelper.getChannelUser(channel);
                    if(user!=null)
                        list.add(user);
                }
            }
            System.out.println("GetGroupMembersRequestHandler");
            //数据库内容
            GroupInfo groupInfo=GroupInfoService.getGroupInfoById(groupId);
            List<GroupMembers> groupMemberslist = GroupMembersService.getGroupMembersByGroupId(groupId);
            List<ChannelUser> userList=new ArrayList<>();
            if(groupMemberslist==null||groupMemberslist.size()==0){
                System.out.println("GetGroupMembersRequestHandler,groupMemberslist为空");
                responseFalse(ctx);
            }
            //TODO 19点27分 做到这儿
            //System.out.println("groupMemberslist:"+groupMemberslist);
            for (GroupMembers members : groupMemberslist) {
                //System.out.println("进入循环");
                User user = UserService.getUserById(members.getGroupUserid());
                if(user!=null){
                    //System.out.println("获取了user");
                    ChannelUser channelUser=new ChannelUser(user.getId(),user.getUsername());
                    userList.add(channelUser);
                }else{
                    //System.out.println("userid不存在");
                }
            }
            //System.out.println("开始组装消息包,channelUser="+userList);
            GetGroupMembersResponsePacket getGroupMembersResponsePacket=new GetGroupMembersResponsePacket();
            getGroupMembersResponsePacket.setGroupId(groupId);
            getGroupMembersResponsePacket.setGroupInfo(groupInfo);
            getGroupMembersResponsePacket.setOnlineUserList(list);
            getGroupMembersResponsePacket.setAllUserList(userList);
            getGroupMembersResponsePacket.setSuccess(true);
            getGroupMembersResponsePacket.setReason(null);

            //getGroupMembersResponsePacket.setChannelUserList(list);

            ctx.writeAndFlush(getGroupMembersResponsePacket);
            System.out.println("GetGroupMembersRequestHandler,已经发送");
        });
    }
    public void responseFalse(ChannelHandlerContext ctx){
        GetGroupMembersResponsePacket getGroupMembersResponsePacket=new GetGroupMembersResponsePacket();
        getGroupMembersResponsePacket.setSuccess(false);
        getGroupMembersResponsePacket.setReason("数据库取出失败，请检查是否没有该群或者该群已解散（没有用户）");
        ctx.writeAndFlush(getGroupMembersResponsePacket);
    }
}
