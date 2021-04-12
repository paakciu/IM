package top.paakciu.client.manage;

import io.netty.channel.Channel;
import top.paakciu.client.handler.GetGroupMembersResponseHandler;
import top.paakciu.protocal.packet.OffLineMessageRequestPacket;
import top.paakciu.utils.AttributesHelper;

/**
 * @author paakciu
 * @ClassName: GetOffLineMessageManage
 * @date: 2021/4/12 14:25
 */
public class GetOffLineMessageManage extends BaseManageWith2Function<GetOffLineMessageManage>{

    public GetOffLineMessageManage(Channel channel) {
        super(channel);
        setSon(this);
    }

    public GetOffLineMessageManage getOffLineMessage(){
        OffLineMessageRequestPacket offLineMessageRequestPacket = new OffLineMessageRequestPacket();
        offLineMessageRequestPacket.setId(AttributesHelper.getChannelUser(channel).getUserId());

        writeAndFlushAddListener(offLineMessageRequestPacket);
        return this;
    }
}
