package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.OffLineGroupMessageResponseHandler;
import top.paakciu.protocal.packet.OffLineGroupMessageRequestPacket;
import top.paakciu.protocal.packet.OffLineGroupMessageResponsePacket;

/**
 * @author paakciu
 * @ClassName: OffLineGroupMessageManage
 * @date: 2021/4/15 15:53
 */
public class OffLineGroupMessageManage extends BaseManageWith4Function<OffLineGroupMessageResponsePacket, OffLineGroupMessageResponseHandler,OffLineGroupMessageManage>{

    public OffLineGroupMessageManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public OffLineGroupMessageManage getOffLineGroupMessage(){
        OffLineGroupMessageRequestPacket requestPacket=new OffLineGroupMessageRequestPacket();
        //requestPacket.setUserId(userId);
        writeAndFlushAddListener(requestPacket);
        return this;
    }
}
