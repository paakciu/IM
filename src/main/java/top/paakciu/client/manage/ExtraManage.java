package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.ExtraResponseHandler;
import top.paakciu.client.listener.HandlerListener;
import top.paakciu.protocal.packet.ExtraRequestPacket;
import top.paakciu.protocal.packet.ExtraResponsePacket;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.ExtraPacketHelper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author paakciu
 * @ClassName: ExtraManage
 * @date: 2021/4/12 21:10
 */
public class ExtraManage extends BaseManageWithHandlerFunction<ExtraResponsePacket,ExtraResponseHandler,ExtraManage>{
    public ExtraManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public <T> ExtraManage sendExtraMessage(Long toid,T msg,Integer type,Class<T> clazz){
        ExtraRequestPacket extraRequestPacket=new ExtraRequestPacket();
        ExtraPacketHelper.toByte(clazz,type,msg,extraRequestPacket);
        extraRequestPacket.setToUserId(toid);
        extraRequestPacket.setFromUserId(AttributesHelper.getChannelUser(channel).getUserId());
        writeAndFlushAddListener(extraRequestPacket);
        return this;
    }


}
