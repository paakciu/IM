package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: ExtraGroupResponsePacket
 * @date: 2021/4/3 16:45
 */
public class ExtraGroupResponsePacket extends BasePacket{
    private Long fromUserId;
    private String fromUserName;
    //扩展包的类型---需要自行映射
    private Integer type;
    //扩展包的字节流长度，使用int，带符号数上限只能2GB
    private Integer len;
    //扩展包的字节流内容
    private byte[] message;
}
