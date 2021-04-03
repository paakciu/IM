package top.paakciu.protocal.packet;

import top.paakciu.utils.ChannelUser;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetGroupMembersResponsePacket
 * @date: 2021/4/3 15:56
 */
public class GetGroupMembersResponsePacket extends BasePacket{

    private Long groupId;
    private List<ChannelUser> channelUserList;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<ChannelUser> getChannelUserList() {
        return channelUserList;
    }

    public void setChannelUserList(List<ChannelUser> channelUserList) {
        this.channelUserList = channelUserList;
    }

}
