package top.paakciu.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author paakciu
 * @ClassName: AttributesHelper
 * @date: 2021/3/5 12:24
 */
public class AttributesHelper {
    public static AttributeKey<Integer> LOGIN=AttributeKey.newInstance("login");
    public static AttributeKey<ChannelUser> ChannelUser=AttributeKey.newInstance("ChannelUser");
    //Session映射表
    private static final Map<Long, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    //public static AttributeKey<Session> SESSION=AttributeKey.newInstance("session");


    public static void asLogin(Channel ch)
    {
        ch.attr(LOGIN).set(1);
        //System.out.println("设置登录");
    }
    public static void asLogout(Channel ch)
    {
        ch.attr(LOGIN).set(0);
        //System.out.println("设置登录");
    }
    public static boolean hasLogin(Channel channel) {
        Attribute<Integer> loginAttr = channel.attr(LOGIN);
        //System.out.println("判断登录="+loginAttr.get() != null&&loginAttr.get()==true);
        return (loginAttr.get() != null&&loginAttr.get()==1);
    }
    





    public static void setChannelUser(Channel channel,ChannelUser channelUser) {
        userIdChannelMap.put(channelUser.getUserId(), channel);
        channel.attr(ChannelUser).set(channelUser);
    }

    public static void removeChannelUser(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getChannelUser(channel).getUserId());
            channel.attr(ChannelUser).set(null);
        }
    }
    public static ChannelUser getChannelUser(Channel channel) {
        return channel.attr(ChannelUser).get();
    }

    public static Channel getChannel(Long userId) {
        return userIdChannelMap.get(userId);
    }
}



//                AttributeKey.exists("login")
//                        ?AttributeKey.valueOf("login")
//                        :AttributeKey.newInstance("login");
//    public enum LOGINSTATE{
//        LOGINING(0),
//        LOGINOK(1),
//        RELOGIN(2),
//        ERROR(3);
//
//    private Integer value;
//        LOGINSTATE(Integer i){this.value=i;}
//
//    public Integer getValue() {
//        return value;
//    }
//
//    public void setValue(Integer value) {
//        this.value = value;
//    }
//}

//    public static Integer getLoginState(Channel channel){
//        return channel.attr(LOGIN).get();
//    }
//    public static boolean setLoginState(Channel channel,Integer should,Integer state){
//        return channel.attr(LOGIN).compareAndSet(should,state);
//    }
//    public static void setLoginState(Channel channel,Integer state){
//        channel.attr(LOGIN).set(state);
//    }

