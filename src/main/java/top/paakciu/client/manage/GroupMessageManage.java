package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.GroupMessageResponseHandler;
import top.paakciu.protocal.packet.GroupMessageRequestPacket;
import top.paakciu.protocal.packet.GroupMessageResponsePacket;

public class GroupMessageManage extends BaseManageWithHandlerFunction<GroupMessageResponsePacket, GroupMessageResponseHandler,GroupMessageManage> {
    public GroupMessageManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public GroupMessageManage sendGroupMessage(Long toGroupId,String msg){
        GroupMessageRequestPacket groupMessageRequestPacket=new GroupMessageRequestPacket();
        groupMessageRequestPacket.setToGroupId(toGroupId);
        groupMessageRequestPacket.setMessage(msg);
        writeAndFlushAddListener(groupMessageRequestPacket);
        return this;
    }
}
