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
//    public final static Byte PULL_MESSAGE_REQUEST2=24;//TEST PullMessageRequestPacket



    //双向映射表
    private final static HashMap<Class<? extends BasePacket>,Byte> map=new HashMap<>();
    private final static ArrayList<Class<? extends BasePacket>> list=new ArrayList<>();

    static {
        list.add(LoginRequestPacket.class);//0
        list.add(LoginResponsePacket.class);//1
        list.add(MessageRequestPacket.class);//2
        list.add(MessageResponsePacket.class);//3
        list.add(RegisterRequestPacket.class);//4
        list.add(RegisterResponsePacket.class);//5
        list.add(ErrorMessagePacket.class);//6
        list.add(HeartBeatPacket.class);//7
        list.add(CreateGroupRequestPacket.class);//8
        list.add(CreateGroupResponsePacket.class);//9
        list.add(JoinGroupRequestPacket.class);//10
        list.add(JoinGroupResponsePacket.class);//11
        list.add(QuitGroupRequestPacket.class);//12
        list.add(QuitGroupResponsePacket.class);//13
        list.add(GetGroupMembersRequestPacket.class);//14
        list.add(GetGroupMembersResponsePacket.class);//15
        list.add(GroupMessageRequestPacket.class);//16
        list.add(GroupMessageResponsePacket.class);//17
        list.add(ExtraRequestPacket.class);//18
        list.add(ExtraResponsePacket.class);//19
        list.add(ExtraGroupRequestPacket.class);//20
        list.add(ExtraGroupResponsePacket.class);//21
        list.add(PullMessageRequestPacket.class);//22
        list.add(OffLineMessageRequestPacket.class);//OffLineMessageRequestPacket 23
//        list.add(PullMessageRequestPacket2.class);//PullMessageRequestPacket2 24

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
//        map.put(PullMessageRequestPacket2.class,PULL_MESSAGE_REQUEST2);//PullMessageRequestPacket2 24

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
