package top.paakciu.protocal.packet;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: CreateGroupResponsePacket
 * @date: 2021/4/2 14:00
 */
public class CreateGroupResponsePacket extends BasePacket{
    private boolean success;
    private long groupId;
    private List<Long> userNameList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public List<Long> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<Long> userNameList) {
        this.userNameList = userNameList;
    }

}
