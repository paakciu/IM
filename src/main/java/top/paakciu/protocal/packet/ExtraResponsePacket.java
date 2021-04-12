package top.paakciu.protocal.packet;

import java.util.Arrays;
import java.util.Date;

/**
 * @author paakciu
 * @ClassName: ExtraResponsePacket
 * @date: 2021/4/3 16:27
 */
public class ExtraResponsePacket extends BasePacket{
    private Long fromUserId;
    private String fromUserName;
    private Long toUserId;
    //扩展包的类型---需要自行映射
    private Integer type;
    //扩展包的字节流长度，使用int，带符号数上限只能2GB
    private Integer len;
    //扩展包的字节流内容
    private byte[] message;
    //时间
    private Date date;


    //region getter setter and toString
    @Override
    public String toString() {
        return "ExtraResponsePacket{" +
                "fromUserId=" + fromUserId +
                ", fromUserName='" + fromUserName + '\'' +
                ", toUserId=" + toUserId +
                ", type=" + type +
                ", len=" + len +
                ", message=" + Arrays.toString(message) +
                ", date=" + date +
                '}';
    }

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

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    //endregion
}
