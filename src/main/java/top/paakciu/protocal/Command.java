package top.paakciu.protocal;

import top.paakciu.protocal.packet.BasePacket;
import top.paakciu.protocal.packet.LoginRequestPacket;
import top.paakciu.protocal.packet.LoginResponsePacket;
import top.paakciu.protocal.packet.MessagePacket;

/**
 * 指示消息的什么类型的常量类
 */
public final class Command {
    //命令的编号
    public final static Byte LOGIN_REQUEST = 1;
    public final static Byte LOGIN_RESPONSE=2;
    public final static Byte MESSAGE=3;

    //取出命令对应的类
    public static Class<? extends BasePacket> getRequestType(Byte command)
    {
        if(command==LOGIN_REQUEST)
            return new LoginRequestPacket().getClass();
        else if(command==LOGIN_RESPONSE)
            return new LoginResponsePacket().getClass();
        else if(command==MESSAGE)
            return new MessagePacket().getClass();
        return null;
    }
}
