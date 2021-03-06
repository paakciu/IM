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
    public static AttributeKey<Boolean> LOGIN=AttributeKey.newInstance("login");
    public static AttributeKey<Session> SESSION=AttributeKey.newInstance("session");
//                AttributeKey.exists("login")
//                        ?AttributeKey.valueOf("login")
//                        :AttributeKey.newInstance("login");


    public static void asLogin(Channel ch)
    {
        ch.attr(LOGIN).set(true);
        System.out.println("设置登录");
    }
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(LOGIN);
        //System.out.println("判断登录="+loginAttr.get() != null&&loginAttr.get()==true);
        return (loginAttr.get() != null&&loginAttr.get()==true);
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
