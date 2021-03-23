package top.paakciu.protocal.packet;

import org.apache.ibatis.annotations.Insert;

/**
 * @author paakciu
 * @ClassName: RegisterResponsePacket
 * @date: 2021/3/22 16:52
 */
public class RegisterResponsePacket extends BasePacket{
    private boolean success;
    private String msg;

    public RegisterResponsePacket() {
    }

    public RegisterResponsePacket(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
