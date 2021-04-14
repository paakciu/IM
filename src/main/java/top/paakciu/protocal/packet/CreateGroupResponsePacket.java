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
    private String groupName;

    private Long createUserId;
    private Long createUserName;

    private List<String> userNameList;
    private List<Long> userIdList;


    public Long getCreateUserName() {
        return createUserName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreateUserName(Long createUserName) {
        this.createUserName = createUserName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

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

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }
}
