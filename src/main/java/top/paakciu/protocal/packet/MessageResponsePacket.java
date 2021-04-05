package top.paakciu.protocal.packet;

import java.util.Date;

/**
 * @ClassName: MessageResponsePacket 
 * @author paakciu
 * @date: 2021/3/18 13:24
 */
public class MessageResponsePacket extends BasePacket{
//    private Boolean success;
    private Long fromUserId;
    private String fromUserName;
    private String message;
    private Date date=new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //    public Boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(Boolean success) {
//        this.success = success;
//    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
