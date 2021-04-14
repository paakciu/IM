package top.paakciu.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.service.GroupInfoService;
import top.paakciu.service.GroupMembersService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 需要解决的是，channel在哪些群里，群id里有哪些channel，群id是哪个channelgroup
 *
 * 即每个channel绑定他所在的群组-》list group，然后group里面又有 channel
 * @author paakciu
 * @ClassName: GroupsHelper
 * @date: 2021/4/3 14:37
 */
public class GroupsHelper {
    private static final Map<Long, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();
    //private static final Map<Channel, List<ChannelGroup>> userMap=new ConcurrentHashMap<>();

    public static void UserLoginAboutGroup(ChannelHandlerContext ctx,Long userid){
        System.out.println("登录UserLoginAboutGroup");
        //取出这个user所在的所有群组
        List<GroupInfo> groupInfoList = GroupInfoService.getGroupsByUserId(userid);
        //映射所有群组---这是每个用户信道都有一个的
        Map<Long,ChannelGroup> groupMap=new ConcurrentHashMap<>();
        if(groupInfoList==null||groupInfoList.size()==0){
            //目前不在任何的群组

        }
        for (GroupInfo groupInfo : groupInfoList) {
            Long groupId=groupInfo.getId();
            if(containsGroupId(groupId)){
                //如果表中已经存在该群组，取出来即可
                ChannelGroup channelGroup=getChannelGroup(groupId);
                channelGroup.add(ctx.channel());
                groupMap.put(groupId,channelGroup);
                System.out.println("已经有这个群啦，加入在线人就行了"+channelGroup);
            }else{
                //如果表中没有这个群组，则要在数据库中查找并且建立该表缓存
                //如果群组为空，检查数据库是否有需要从数据库中添加缓存
                GroupInfo info=GroupInfoService.getGroupInfoById(groupId);
                if(info!=null) {
                    //就是说有这个群组，直接获取群组--在线人
                    List<GroupMembers> list = GroupMembersService.getGroupMembersByGroupId(groupId);
                    ChannelGroup group = new DefaultChannelGroup(ctx.executor());
                    for (GroupMembers members : list) {
                        Long tempuserId = members.getId();
                        Channel channel = AttributesHelper.getChannel(tempuserId);
                        if (channel != null)
                            group.add(channel);
                    }
                    //修复群组缺失的群
                    addGroup(groupId, group);
                    ChannelGroup channelGroup=getChannelGroup(groupId);
                    channelGroup.add(ctx.channel());//重复他会自动冲突
                    groupMap.put(groupId,channelGroup);
                    System.out.println("已经添加到群组中"+GroupsHelper.getChannelGroup(groupId));
                } else{
                    //todo 如果数据库中都没有该群组，但是members却有，证明出现了问题.

                }
            }
        }
        //链接群组
        AttributesHelper.setChannelGroup(ctx.channel(),groupMap);

    }
    public static void UserLogoutAboutGroup(Channel channel){
        System.out.println("退出登录UserLogoutAboutGroup");
        //清除所有在线群组关于userid的消息
        Map<Long, ChannelGroup> channelGroupMap = AttributesHelper.getChannelGroup(channel);
        if(channelGroupMap==null)return;
        for (Long groupid : channelGroupMap.keySet()) {
            ChannelGroup group= channelGroupMap.get(groupid);
            Long userid=AttributesHelper.getChannelUser(channel).getUserId();
            group.remove(userid);
        }
        AttributesHelper.removeChannelGroup(channel);
    }
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

    public static Long getGroupId(ChannelGroup group){
        if(!channelGroupMap.containsValue(group))return null;

        for (Long key : channelGroupMap.keySet()) {
            if(getChannelGroup(key)==group){
                return key;
            }
        }
        return null;
    }

}
