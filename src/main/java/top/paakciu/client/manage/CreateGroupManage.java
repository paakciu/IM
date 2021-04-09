package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.CreateGroupResponseHandler;
import top.paakciu.client.handler.MessageResponseHandler;
import top.paakciu.client.listener.FailListener;
import top.paakciu.client.listener.SendFailListener;
import top.paakciu.client.listener.SendSuccessListener;
import top.paakciu.client.listener.SuccessListener;
import top.paakciu.protocal.packet.CreateGroupRequestPacket;
import top.paakciu.protocal.packet.CreateGroupResponsePacket;

import java.util.List;

public class CreateGroupManage extends BaseManageWith4Function<CreateGroupResponsePacket,CreateGroupResponseHandler,CreateGroupManage>{


    public CreateGroupManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public CreateGroupManage createGroup(List<Long> userList){
        CreateGroupRequestPacket createGroupRequestPacket=new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(userList);
        channel.writeAndFlush(createGroupRequestPacket).addListener(future -> {
            if(future.isSuccess()){
                if(listeners.sendsuccessListener!=null)
                    listeners.sendsuccessListener.onSendSuccess();
            }else{
                if(listeners.sendfailListener!=null)
                    listeners.sendfailListener.onSendFail();
            }
        });
        return this;
    }
}
