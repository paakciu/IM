package top.paakciu.service;

import top.paakciu.mbg.mapper.GroupMsgMapper;
import top.paakciu.mbg.mapper.GroupMsgOfflineMapper;
import top.paakciu.mbg.model.GroupMsg;
import top.paakciu.mbg.model.GroupMsgOffline;
import top.paakciu.mbg.model.GroupMsgOfflineExample;
import top.paakciu.utils.Sqlutils;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GroupMessageOfflineService
 * @date: 2021/4/15 13:18
 */
public class GroupMessageOfflineService {
    private static int addGroupOfflineMessage(GroupMsgOffline groupMsgOffline){
        return Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,(mapper)->{
            return mapper.insert(groupMsgOffline);
        });
    }

    private static int addGroupOfflineMessage(Long groupid,Long toid,Long groupmsgid){
        GroupMsgOffline groupMsgOffline=new GroupMsgOffline();
        groupMsgOffline.setGroupid(groupid);
        groupMsgOffline.setToid(toid);
        groupMsgOffline.setFirstgroupmsgid(groupmsgid);
        return Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,(mapper)->{
            return mapper.insert(groupMsgOffline);
        });
    }
    public static Integer addOnlyGroupOfflineMessage(Long groupid,Long toid,Long groupmsgid){

        List<GroupMsgOffline> list=Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,(mapper)->{
            GroupMsgOfflineExample example=new GroupMsgOfflineExample();
            example.createCriteria().andGroupidEqualTo(groupid).andToidEqualTo(toid);
            return mapper.selectByExample(example);
        });
        if(list==null||list.size()==0){
            //要插入冲突
            System.out.println("没有冲突消息，进入持久化！");
            GroupMsgOffline groupMsgOffline=new GroupMsgOffline();
            groupMsgOffline.setGroupid(groupid);
            groupMsgOffline.setToid(toid);
            groupMsgOffline.setFirstgroupmsgid(groupmsgid);
            Integer num = Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,(mapper)->{
                return mapper.insert(groupMsgOffline);
            });
            System.out.println("持久化完成！return="+num);
            return num;
        }
        return null;
    }

    public static Long getcontainsByGroupidAndToid(Long groupid,Long toid){

        List<GroupMsgOffline> list=Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,(mapper)->{
            GroupMsgOfflineExample example=new GroupMsgOfflineExample();
            example.createCriteria().andGroupidEqualTo(groupid).andToidEqualTo(toid);
            return mapper.selectByExample(example);
        });
        if(list==null||list.size()==0){
            //这是要插入的
            return null;
        }else{
            //这是冲突的
            if(list.size()==1){
                //正常,取出最后的id
                GroupMsgOffline msg=list.get(0);
                if(msg!=null){
                    Long id=msg.getFirstgroupmsgid();
                    if(id!=null)
                        return id;
                }
                return null;
            }
            //不正常
            return null;
        }
    }
    public static List<GroupMsgOffline> getGroupMessageOfflineByToid(Long toid){
        return Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,mapper->{
            GroupMsgOfflineExample example=new GroupMsgOfflineExample();
            example.createCriteria().andToidEqualTo(toid);
            return mapper.selectByExample(example);
        });
    }
    public static Integer deleteGroupMessageOfflineByToid(Long toid){
        return Sqlutils.startSqlSession(GroupMsgOfflineMapper.class,mapper->{
            GroupMsgOfflineExample example=new GroupMsgOfflineExample();
            example.createCriteria().andToidEqualTo(toid);
            return mapper.deleteByExample(example);
        });
    }
}
