package top.paakciu.protocal.packet;

import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMsg;
import top.paakciu.mbg.model.GroupMsgOffline;

import java.util.List;
import java.util.Map;

/**
 * @author paakciu
 * @ClassName: OffLineGroupMessageResponsePacket
 * @date: 2021/4/15 15:48
 */
public class OffLineGroupMessageResponsePacket extends BasePacket{

    private boolean success;
    private List<GroupMsgOffline> groupMsgOfflineList;
    //groupid->GroupInfo
    private Map<Long, GroupInfo> groupInfoMap;
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

    public List<GroupMsgOffline> getGroupMsgOfflineList() {
        return groupMsgOfflineList;
    }

    public void setGroupMsgOfflineList(List<GroupMsgOffline> groupMsgOfflineList) {
        this.groupMsgOfflineList = groupMsgOfflineList;
    }

    public Map<Long, GroupInfo> getGroupInfoMap() {
        return groupInfoMap;
    }

    public void setGroupInfoMap(Map<Long, GroupInfo> groupInfoMap) {
        this.groupInfoMap = groupInfoMap;
    }
}
