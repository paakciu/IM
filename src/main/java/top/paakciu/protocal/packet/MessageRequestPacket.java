package top.paakciu.protocal.packet;/**
 * @ClassName: MessageRequestPacket 
 * @author paakciu
 * @date: 2021/3/18 13:23
 */
public class MessageRequestPacket extends BasePacket{
    private Long fromUserId;
    private Long toUserId;
    private String message;

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Long getFromUserId() {
        return fromUserId;
    }
    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
}
