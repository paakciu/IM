package top.paakciu.client.manage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.GetGroupListResponseHandler;
import top.paakciu.protocal.packet.GetGroupListRequestPacket;
import top.paakciu.protocal.packet.GetGroupListResponsePacket;

/**
 * @author paakciu
 * @ClassName: GetGroupListManage
 * @date: 2021/4/15 12:30
 */
public class GetGroupListManage extends BaseManageWith4Function<GetGroupListResponsePacket, GetGroupListResponseHandler, GetGroupListManage>{
    public GetGroupListManage(Channel channel, ChannelHandler handler) {
        super(channel, handler);
        setSon(this);
    }

    public GetGroupListManage getGroupList(Long userId){
        GetGroupListRequestPacket getGroupListRequestPacket=new GetGroupListRequestPacket();
        getGroupListRequestPacket.setUserId(userId);
        writeAndFlushAddListener(getGroupListRequestPacket);
        return this;
    }
}
