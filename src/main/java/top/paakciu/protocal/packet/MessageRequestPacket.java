package top.paakciu.protocal.packet;/**
 * @ClassName: MessageRequestPacket 
 * @author paakciu
 * @date: 2021/3/18 13:23
 */
public class MessageRequestPacket extends BasePacket{
    private String toUserId;
    private String message;

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
