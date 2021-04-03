package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GroupMessageResponsePacket
 * @date: 2021/4/3 16:51
 */
public class GroupMessageResponsePacket extends BasePacket{
    private Long fromUserId;
    //这里是要加入群组ID的，因为一个用户收到了群组消息，也要区分开是哪个群
    private Long toGroupId;
    //private String fromUserName;
    private String message;

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Long toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
