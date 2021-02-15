package top.paakciu.protocal;

/**
 * @author paakciu
 */
public abstract class BasePacket {
    /**
     * 协议版本
     */
    private Byte version=1;

    /**
     * 获取指令的抽象方法
     * @return
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
