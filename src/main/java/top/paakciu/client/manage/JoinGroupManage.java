package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.JoinGroupResponseHandler;
import top.paakciu.client.listener.*;
import top.paakciu.protocal.packet.CreateGroupRequestPacket;
import top.paakciu.protocal.packet.JoinGroupRequestPacket;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;

import java.util.List;

public class JoinGroupManage extends BaseManageWith4Function<JoinGroupResponsePacket,JoinGroupResponseHandler,JoinGroupManage>{


    public JoinGroupManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    //加入群组的逻辑
    public JoinGroupManage joinGroup(Long groupId){
        JoinGroupRequestPacket joinGroupRequestPacket=new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);
//        joinGroupRequestPacket.set
//TODO
        writeAndFlushAddListener(channel,joinGroupRequestPacket,listeners);
        return this;
    }
}
