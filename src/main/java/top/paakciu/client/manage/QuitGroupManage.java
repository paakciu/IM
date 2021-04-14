package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.QuitGroupResponseHandler;
import top.paakciu.client.listener.*;
import top.paakciu.protocal.packet.QuitGroupRequestPacket;
import top.paakciu.protocal.packet.QuitGroupResponsePacket;

public class QuitGroupManage extends BaseManageWith4Function<QuitGroupResponsePacket,QuitGroupResponseHandler,QuitGroupManage>{


    public QuitGroupManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public QuitGroupManage quitGroup(Long groupId,Long userId){
        QuitGroupRequestPacket quitGroupRequestPacket=new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        quitGroupRequestPacket.setUserId(userId);
        writeAndFlushAddListener(quitGroupRequestPacket);
        return this;
    }
}
