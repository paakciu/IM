package top.paakciu.protocal.packet;

import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.utils.info.ChannelUser;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetGroupMembersResponsePacket
 * @date: 2021/4/3 15:56
 */
public class GetGroupMembersResponsePacket extends BasePacket{
    private boolean success;
    private Long groupId;
    //群的信息 todo 这个可能使用GroupInfo不是很好，但是暂时先这样吧
    private GroupInfo groupInfo;
    //群的在线的用户ll
    private List<ChannelUser> OnlineUserList;
    //所有用户信息
    private List<ChannelUser> AllUserList;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<ChannelUser> getOnlineUserList() {
        return OnlineUserList;
    }

    public void setOnlineUserList(List<ChannelUser> onlineUserList) {
        OnlineUserList = onlineUserList;
    }

    public List<ChannelUser> getAllUserList() {
        return AllUserList;
    }

    public void setAllUserList(List<ChannelUser> allUserList) {
        AllUserList = allUserList;
    }
}
