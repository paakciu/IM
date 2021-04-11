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
    public final static Byte JOIN_GROUP_REQUEST=10;
    public final static Byte JOIN_GROUP_RESPONSE=11;
    public final static Byte QUIT_GROUP_REQUEST=12;
    public final static Byte QUIT_GROUP_RESPONSE=13;
    public final static Byte GET_GROUP_MEMBER_REQUEST=14;
    public final static Byte GET_GROUP_MEMBER_RESPONSE=15;
    public final static Byte GROUP_MESSAGE_REQUEST=16;
    public final static Byte GROUP_MESSAGE_RESPONSE=17;
    public final static Byte EXTRA_REQUEST=18;
    public final static Byte EXTRA_RESPONSE=19;
    public final static Byte EXTRA_GROUP_REQUEST=20;
    public final static Byte EXTRA_GROUP_RESPONSE=21;
    public final static Byte PULL_MESSAGE_REQUEST=22;//PullMessageRequestPacket
    public final static Byte OFF_LINE_MESSAGE_REQUEST=23;//OffLineMessageRequestPacket



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
        list.add(JoinGroupRequestPacket.class);
        list.add(JoinGroupResponsePacket.class);
        list.add(QuitGroupRequestPacket.class);
        list.add(QuitGroupResponsePacket.class);
        list.add(GetGroupMembersRequestPacket.class);
        list.add(GetGroupMembersResponsePacket.class);
        list.add(GroupMessageRequestPacket.class);
        list.add(GroupMessageResponsePacket.class);
        list.add(ExtraRequestPacket.class);
        list.add(ExtraResponsePacket.class);
        list.add(ExtraGroupRequestPacket.class);
        list.add(ExtraGroupResponsePacket.class);
        list.add(PullMessageRequestPacket.class);
        list.add(OffLineMessageRequestPacket.class);//OffLineMessageRequestPacket

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
        map.put(JoinGroupRequestPacket.class,JOIN_GROUP_REQUEST);
        map.put(JoinGroupResponsePacket.class,JOIN_GROUP_RESPONSE);
        map.put(QuitGroupRequestPacket.class,QUIT_GROUP_REQUEST);
        map.put(QuitGroupResponsePacket.class,QUIT_GROUP_RESPONSE);
        map.put(GetGroupMembersRequestPacket.class,GET_GROUP_MEMBER_REQUEST);
        map.put(GetGroupMembersResponsePacket.class,GET_GROUP_MEMBER_RESPONSE);
        map.put(GroupMessageRequestPacket.class,GROUP_MESSAGE_REQUEST);
        map.put(GroupMessageResponsePacket.class,GROUP_MESSAGE_RESPONSE);
        map.put(ExtraRequestPacket.class,EXTRA_REQUEST);
        map.put(ExtraResponsePacket.class,EXTRA_RESPONSE);
        map.put(ExtraGroupRequestPacket.class,EXTRA_GROUP_REQUEST);
        map.put(ExtraGroupResponsePacket.class,EXTRA_GROUP_RESPONSE);
        map.put(PullMessageRequestPacket.class,PULL_MESSAGE_REQUEST);
        map.put(OffLineMessageRequestPacket.class,OFF_LINE_MESSAGE_REQUEST);//OffLineMessageRequestPacket

//        for(Byte i=0;i<list.size();i++)
//        {
//            map.put(list.get(i),i);
//        }
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
