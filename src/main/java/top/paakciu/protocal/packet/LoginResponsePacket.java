package top.paakciu.protocal.packet;

import top.paakciu.protocal.Command;

/**
 * @author paakciu
 * @ClassName: LoginResponPacket
 * @date: 2021/2/27 22:37
 */
public class LoginResponsePacket extends BasePacket {

    private String reason;
    private Boolean success=false;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
