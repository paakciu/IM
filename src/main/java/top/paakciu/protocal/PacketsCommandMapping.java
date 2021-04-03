package top.paakciu.protocal;

import top.paakciu.protocal.packet.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 指示消息的什么类型的常量类
 */
public final class PacketsCommandMapping {
    //命令的编号
    public final static Byte LOGIN_REQUEST = 0;
    public final static Byte LOGIN_RESPONSE=1;
    public final static Byte MESSAGE_REQUEST=2;
    public final static Byte MESSAGE_RESPONSE=3;
    public final static Byte REGISTER_REQUEST=4;
    public final static Byte REGISTER_RESPONSE=5;
    public final static Byte ERROR=6;
    public final static Byte HEARTBEAT=7;
    public final static Byte CREATE_GROUP_REQUEST=8;
    public final static Byte CREATE_GROUP_RESPONSE=9;

    //双向映射表
    private final static HashMap<Class<? extends BasePacket>,Byte> map=new HashMap<>();
    private final static ArrayList<Class<? extends BasePacket>> list=new ArrayList<>();

    static {
        list.add(LoginRequestPacket.class);
        list.add(LoginResponsePacket.class);
        list.add(MessageRequestPacket.class);
        list.add(MessageResponsePacket.class);
        list.add(RegisterRequestPacket.class);
        list.add(RegisterResponsePacket.class);
        list.add(ErrorMessagePacket.class);
        list.add(HeartBeatPacket.class);
        list.add(CreateGroupRequestPacket.class);
        list.add(CreateGroupResponsePacket.class);

        map.put(LoginRequestPacket.class,LOGIN_REQUEST);
        map.put(LoginResponsePacket.class,LOGIN_RESPONSE);
        map.put(MessageRequestPacket.class,MESSAGE_REQUEST);
        map.put(MessageResponsePacket.class,MESSAGE_RESPONSE);
        map.put(RegisterRequestPacket.class,REGISTER_REQUEST);
        map.put(RegisterResponsePacket.class,REGISTER_RESPONSE);
        map.put(ErrorMessagePacket.class,ERROR);
        map.put(HeartBeatPacket.class,HEARTBEAT);
        map.put(CreateGroupRequestPacket.class,CREATE_GROUP_REQUEST);
        map.put(CreateGroupResponsePacket.class,CREATE_GROUP_RESPONSE);
    }

    //取出命令对应的类
    public static Class<? extends BasePacket> getRequestType(Byte command)
    {
        return list.get(command);
    }
    public static Byte getCommand(Class<? extends BasePacket> clazz)
    {
        return map.get(clazz);
    }
}
