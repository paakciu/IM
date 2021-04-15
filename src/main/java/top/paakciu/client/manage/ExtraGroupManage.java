package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.ExtraGroupResponseHandler;
import top.paakciu.protocal.packet.ExtraGroupRequestPacket;
import top.paakciu.protocal.packet.ExtraGroupResponsePacket;
import top.paakciu.protocal.packet.ExtraRequestPacket;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ExtraPacketHelper;

/**
 * @author paakciu
 * @ClassName: ExtraGroupManage
 * @date: 2021/4/15 16:57
 */
public class ExtraGroupManage extends BaseManageWithHandlerFunction<ExtraGroupResponsePacket, ExtraGroupResponseHandler,ExtraGroupManage>{
    public ExtraGroupManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public <T> ExtraGroupManage sendExtraGroup(Long groupId,T msg,Integer type){
        ExtraGroupRequestPacket requestPacket=new ExtraGroupRequestPacket();
        //依赖工具类封装
        ExtraPacketHelper.toByte(type,msg,requestPacket);
        requestPacket.setGroupId(groupId);
        requestPacket.setFromUserId(AttributesHelper.getChannelUser(channel).getUserId());
        writeAndFlushAddListener(requestPacket);
        return this;
    }

}
