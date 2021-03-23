package top.paakciu.protocal.packet;

/**
 * @author paakciu
 * @ClassName: RegisterRequestPacket
 * @date: 2021/3/22 16:44
 */
public class RegisterRequestPacket extends BasePacket{
    private String username;
    private String password;

    public RegisterRequestPacket() {
    }

    public RegisterRequestPacket(String username, String password) {
        this.username = username;
        this.password = password;
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
