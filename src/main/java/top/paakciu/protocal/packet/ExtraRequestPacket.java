package top.paakciu.protocal.packet;

import top.paakciu.protocal.SerializerAlgorithm;
import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.serializer.JSONSerializer;

/**
 * @author paakciu
 * @ClassName: ExtraRequestPacket
 * @date: 2021/4/3 16:27
 */
public class ExtraRequestPacket extends BasePacket {
    //发到谁-单聊
    private Long toUserId;
    //扩展包的类型---需要自行映射
    private Integer type;
    //扩展包的字节流长度，使用int，带符号数上限只能2GB
    private Integer len;
    //扩展包的字节流内容
    private byte[] message;

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
}
