package top.paakciu.service;

import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.mapper.GroupMsgMapper;
import top.paakciu.mbg.mapper.NormalMsgMapper;
import top.paakciu.mbg.model.GroupMsg;
import top.paakciu.mbg.model.GroupMsgExample;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgExample;
import top.paakciu.utils.Sqlutils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GroupMessage
 * @date: 2021/4/15 13:18
 */
public class GroupMessageService {
    public static int addGroupMessage(GroupMsg groupMsg){
        return Sqlutils.startSqlSession(GroupMsgMapper.class,(mapper)->{
            return mapper.insert(groupMsg);
        });
    }
    public static Integer addGroupMessage(Long groupid,Long fromid,Date time,String msg){
        GroupMsg groupMsg=new GroupMsg();
        groupMsg.setGroupid(groupid);
        groupMsg.setFromid(fromid);
        groupMsg.setTime(time);
        groupMsg.setMsg(msg);
        return Sqlutils.startSqlSession(GroupMsgMapper.class,(mapper)->{
            return mapper.insert(groupMsg);
        });
    }

    public static List<GroupMsg> getGroupMsgByFromMessageId(Long fromMessageId,Long groupid, boolean isBigger){
        System.out.println("数据库函数");
        //TODO 这个很有可能获取到很大的数据库
        return Sqlutils.startSqlSession(GroupMsgMapper.class,(mapper)->{
            GroupMsgExample example=new GroupMsgExample();
            GroupMsgExample.Criteria criteria = example.createCriteria();
            criteria.andGroupidEqualTo(groupid);
            if(isBigger)
                criteria.andIdGreaterThanOrEqualTo(fromMessageId);
            else
                criteria.andIdLessThanOrEqualTo(fromMessageId);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBs(example);
        });
    }
    //setAsGroupBySize(int size)
    public static List<GroupMsg> getGroupMsgBySize(Long groupId,int size){
        return getGroupMsgByOffsetAndSize(groupId,0,size);
    }
    public static List<GroupMsg> getGroupMsgByOffsetAndSize(Long groupId,int offset,int size){
        return Sqlutils.startSqlSession(GroupMsgMapper.class,(mapper)->{
            GroupMsgExample example=new GroupMsgExample();
            GroupMsgExample.Criteria criteria = example.createCriteria();
            criteria.andGroupidEqualTo(groupId);

            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        });
    }

    //setAsGroupByFromMessageIdAndSize(Long fromMessageId1,boolean Bigger,int Size)
    public static List<GroupMsg> getGroupMsgByFromMessageIdAndSize(Long fromMessageId,Long groupId,boolean Bigger,int size){
        return Sqlutils.startSqlSession(GroupMsgMapper.class,mapper -> {
            GroupMsgExample example=new GroupMsgExample();
            GroupMsgExample.Criteria criteria = example.createCriteria();
            criteria.andGroupidEqualTo(groupId);
            if(Bigger)
                criteria.andIdGreaterThanOrEqualTo(fromMessageId);
            else
                criteria.andIdLessThanOrEqualTo(fromMessageId);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(0,size));
        });
    }
}
