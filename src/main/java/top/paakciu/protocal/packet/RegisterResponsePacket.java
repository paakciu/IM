package top.paakciu.protocal.packet;

import org.apache.ibatis.annotations.Insert;

/**
 * @author paakciu
 * @ClassName: RegisterResponsePacket
 * @date: 2021/3/22 16:52
 */
public class RegisterResponsePacket extends BasePacket{
    //请求是否成功
    private boolean success;
    //回复错误码
    //private byte type;
    //回复原因，错误码其实可以跟原因绑定。但是这也服务器更新，客户端必须也跟着更新
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
