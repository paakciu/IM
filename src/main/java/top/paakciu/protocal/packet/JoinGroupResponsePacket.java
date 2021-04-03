package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: JoinGroupResponsePacket
 * @date: 2021/4/3 15:02
 */
public class JoinGroupResponsePacket extends BasePacket{
    private boolean success;
    private Long groupId;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
