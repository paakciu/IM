package top.paakciu.protocal.packet;


import top.paakciu.protocal.Command;

/**
 * LoginRequestPacket
 * @author paakciu
 * @date 2020/12/7 16:56
 */
public class LoginRequestPacket  extends BasePacket {
    
    //登录的普通信息
    private String userId;
    private String username;
    private String password;

    public LoginRequestPacket() {
    }
    
    public LoginRequestPacket(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }


    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
