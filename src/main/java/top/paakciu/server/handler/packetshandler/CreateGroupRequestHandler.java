package top.paakciu.server.handler.packetshandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.paakciu.mbg.mapper.GroupInfoMapper;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.User;
import top.paakciu.protocal.PacketsCommandMapping;
import top.paakciu.protocal.packet.CreateGroupRequestPacket;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.server.NettyServer;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMembersService;
import top.paakciu.service.UserService;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.GroupsHelper;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GreateGroupRequestHandler
 * @date: 2021/4/2 13:54
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        NettyServer.executor.submit(()->{
            //get
            List<Long> userIdList=msg.getUserIdList();
            Long createUserId=msg.getCreateUserid();
            //ref
            List<String> userNameList=new ArrayList<>();

            GroupInfo groupInfo=new GroupInfo();
            groupInfo.setGroupName(msg.getGroupName());
            groupInfo.setUserid(msg.getCreateUserid());
            //持久化两个表

            //region GroupInfo
            int ref=GroupInfoService.addGroupInfo(groupInfo);
            Long groupId=groupInfo.getId();
            if(ref==0){
                //这里失败了，要进行失败处理
                handleError(ctx.channel(),"持久化失败");
                return;
            }
            //endregion

            //region GroupMembers
            ref=GroupMembersService.addGroupMembers(groupId,userIdList);
            if(ref!=userIdList.size()){
                System.out.println("插入的数目对不上，有可能出现了一点问题");
                handleError(ctx.channel(),"持久化群组成员时出现一些问题，插入成员的数目对不上");
                //TODO 根据需要来返回--return ;
            }
            //endregion

            //region 数据库中获取Username
            //数据库中获取Username
            List<User> userList= UserService.getUserListByIdList(userIdList);
            for (User user : userList) {
                userNameList.add(user.getUsername());
            }
            //endregion

            //分组--这是在线的人
            ChannelGroup channelGroup=new DefaultChannelGroup(ctx.executor());
            //构建群组
            for (Long userid : userIdList) {
                Channel channel= AttributesHelper.getChannel(userid);
                if(channel!=null){
                    channelGroup.add(channel);
                }
            }

            //加入在线群聊
            GroupsHelper.addGroup(groupId,channelGroup);

            //创建群聊
            CreateGroupResponsePacket createGroupResponsePacket=new CreateGroupResponsePacket();
            createGroupResponsePacket.setSuccess(true);
            createGroupResponsePacket.setCreateUserId(createUserId);
            createGroupResponsePacket.setUserIdList(userIdList);
            createGroupResponsePacket.setUserNameList(userNameList);
            createGroupResponsePacket.setGroupId(groupId);
            createGroupResponsePacket.setGroupName(msg.getGroupName());
            //todo 离线用户也要告诉他们已经进入了这个群呀，或者不用，如果群组没有消息，建群通知就不通知了

            channelGroup.writeAndFlush(createGroupResponsePacket);

            System.out.println("群创建成功，群里面有："+createGroupResponsePacket.getUserNameList());
        });
    }
    public void handleError(Channel channel,String reason){
        ErrorMessagePacket errorMessagePacket=new ErrorMessagePacket();
        errorMessagePacket.setSuccess(false);
        errorMessagePacket.setErrorCode(PacketsCommandMapping.CREATE_GROUP_REQUEST);
        errorMessagePacket.setReason(reason);
        channel.writeAndFlush(errorMessagePacket);
    }
}
