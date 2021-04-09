package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.GetGroupMembersResponseHandler;
import top.paakciu.client.listener.HandlerListener;
import top.paakciu.client.listener.SendFailListener;
import top.paakciu.client.listener.SendSuccessListener;
import top.paakciu.protocal.packet.GetGroupMembersRequestPacket;
import top.paakciu.protocal.packet.GetGroupMembersResponsePacket;

public class GetGroupMembersManage extends BaseManageWithHandlerFunction<GetGroupMembersResponsePacket, GetGroupMembersResponseHandler,GetGroupMembersManage>{

    public GetGroupMembersManage(Channel channel,ChannelHandler handler) {
        super(channel,handler);
        //一定要写上这个才能返回正确的类型
        setSon(this);
    }

    public void getGroupMembers(Long groupid){
        GetGroupMembersRequestPacket getGroupMembersRequestPacket=new GetGroupMembersRequestPacket();
        getGroupMembersRequestPacket.setGroupId(groupid);

        writeAndFlushAddListener(getGroupMembersRequestPacket);
    }

}
