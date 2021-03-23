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
    public static AttributeKey<Session> SESSION=AttributeKey.newInstance("session");
//                AttributeKey.exists("login")
//                        ?AttributeKey.valueOf("login")
//                        :AttributeKey.newInstance("login");
    public enum LOGINSTATE{
        LOGINING(0),
        LOGINOK(1),
        RELOGIN(2),
        ERROR(3);

    private Integer value;
        LOGINSTATE(Integer i){this.value=i;}

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

    public static void asLogin(Channel ch)
    {
        ch.attr(LOGIN).set(LOGINSTATE.LOGINOK.getValue());
        System.out.println("设置登录");
    }
    public static boolean hasLogin(Channel channel) {
        Attribute<Integer> loginAttr = channel.attr(LOGIN);
        //System.out.println("判断登录="+loginAttr.get() != null&&loginAttr.get()==true);
        return (loginAttr.get() != null&&loginAttr.get()==1);
    }
    public static Integer getLoginState(Channel channel){
        return channel.attr(LOGIN).get();
    }
    public static boolean setLoginState(Channel channel,Integer should,Integer state){
        return channel.attr(LOGIN).compareAndSet(should,state);
    }
    public static void setLoginState(Channel channel,Integer state){
        channel.attr(LOGIN).set(state);
    }


    //Session映射表
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();


    public static void bindSession(Channel channel,Session session) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(SESSION).set(null);
        }
    }
    public static Session getSession(Channel channel) {

        return channel.attr(SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }
}
