package top.paakciu.protocal.packet;

import top.paakciu.protocal.PacketsCommandMapping;

/**
 * @author paakciu
 * @ClassName: LoginResponPacket
 * @date: 2021/2/27 22:37
 */
public class LoginResponsePacket extends BasePacket {

    private Boolean success=false;
    private String reason;
    private Long userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


//    @Override
//    public Byte getCommand() {
//        return PacketsCommandMapping.LOGIN_RESPONSE;
//    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
