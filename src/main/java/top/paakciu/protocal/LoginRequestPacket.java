package top.paakciu.protocal;


import top.paakciu.protocal.constant.Command;

/**
 * LoginRequestPacket
 * @author paakciu
 * @date 2020/12/7 16:56
 */
public class LoginRequestPacket  extends BasePacket{
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

}
