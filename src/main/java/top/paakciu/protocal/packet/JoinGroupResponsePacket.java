package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: JoinGroupResponsePacket
 * @date: 2021/4/3 15:02
 */
public class JoinGroupResponsePacket extends BasePacket{
    private boolean success;
    private boolean onLine;
    private boolean newJoin;
    private Long groupId;
    private Long userId;
    private String userName;
    private String reason;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isNewJoin() {
        return newJoin;
    }

    public void setNewJoin(boolean newJoin) {
        this.newJoin = newJoin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
