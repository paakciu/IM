package top.paakciu.protocal.packet;

import top.paakciu.mbg.model.GroupInfo;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GetGroupListResponsePacket
 * @date: 2021/4/15 12:23
 */
public class GetGroupListResponsePacket extends BasePacket{
    private Long userId;
    private List<GroupInfo> groupInfoList;
    private boolean success;
    private String reason;

    public boolean isSuccess() {
        return success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<GroupInfo> getGroupInfoList() {
        return groupInfoList;
    }

    public void setGroupInfoList(List<GroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
    }
}
