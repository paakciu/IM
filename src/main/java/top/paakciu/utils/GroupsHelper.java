package top.paakciu.utils;

import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author paakciu
 * @ClassName: GroupsHelper
 * @date: 2021/4/3 14:37
 */
public class GroupsHelper {
    private static final Map<Long, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();
    public static boolean addGroup(Long groupId,ChannelGroup channelGroup) {
        if(channelGroupMap.containsKey(groupId))
            return false;
        ChannelGroup ref=channelGroupMap.put(groupId,channelGroup);
        if(ref==null)
            return false;
        return true;
    }
    public static boolean containsGroupId(Long groupId){
        return channelGroupMap.containsKey(groupId);
    }
    public static ChannelGroup getChannelGroup(Long groupId){
        return channelGroupMap.get(groupId);
    }
    public static boolean removeGroup(Long groupId,ChannelGroup channelGroup){
        return channelGroupMap.remove(groupId,channelGroup);
    }
    public static boolean removeGroup(Long groupId){
        ChannelGroup channelGroup= channelGroupMap.remove(groupId);
        if(channelGroup==null)return false;
        return true;
    }

}
