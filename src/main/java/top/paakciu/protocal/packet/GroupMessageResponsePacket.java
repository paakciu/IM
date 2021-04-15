package top.paakciu.protocal.packet;

import java.util.Date;

/**
 * @author paakciu
 * @ClassName: GroupMessageResponsePacket
 * @date: 2021/4/3 16:51
 */
public class GroupMessageResponsePacket extends BasePacket{

    //这里是要加入群组ID的，因为一个用户收到了群组消息，也要区分开是哪个群
    private Long toGroupId;
    private String message;
    private Long fromUserId;
    private String fromUserName;
    private Date date;
    private Long messageId;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
