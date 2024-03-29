package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: GroupMessageRequestPacket
 * @date: 2021/4/3 16:48
 */
public class GroupMessageRequestPacket extends BasePacket{
    private Long toGroupId;
    //private Long fromUserId;
    private String message;

//    public Long getFromUserId() {
//        return fromUserId;
//    }
//
//    public void setFromUserId(Long fromUserId) {
//        this.fromUserId = fromUserId;
//    }

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
