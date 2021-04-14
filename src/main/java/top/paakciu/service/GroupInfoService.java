package top.paakciu.service;

import top.paakciu.mbg.mapper.GroupInfoMapper;
import top.paakciu.mbg.mapper.GroupMembersMapper;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupInfoExample;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.GroupMembersExample;
import top.paakciu.utils.Sqlutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: GroupInfoService
 * @date: 2021/4/13 13:58
 */
public class GroupInfoService {
    public static int addGroupInfo(GroupInfo groupInfo){
        return Sqlutils.startSqlSession(GroupInfoMapper.class,mapper->{
            return mapper.insert(groupInfo);
        });
    }
    public static GroupInfo getGroupInfoById(long groupId){
        return Sqlutils.startSqlSession(GroupInfoMapper.class,mapper->{
            return mapper.selectByPrimaryKey(groupId);
        });
    }

    public static List<GroupInfo> getGroupsByUserId(Long userId){
        return Sqlutils.startSqlSessionByListener((sqlSession)->{
            GroupMembersMapper groupMembersMapper = sqlSession.getMapper(GroupMembersMapper.class);
            GroupInfoMapper groupInfoMapper = sqlSession.getMapper(GroupInfoMapper.class);

            GroupMembersExample example=new GroupMembersExample();
            example.createCriteria().andGroupUseridEqualTo(userId);
            List<GroupMembers> membersList=groupMembersMapper.selectByExample(example);
            if(membersList==null)return new ArrayList<GroupInfo>();
            List<Long> groupIdList=new ArrayList<>();
            for (GroupMembers members : membersList) {
                groupIdList.add(members.getGroupId());
            }
            if(groupIdList.size()==0)return new ArrayList<GroupInfo>();
            GroupInfoExample groupInfoExample=new GroupInfoExample();
            groupInfoExample.createCriteria().andIdIn(groupIdList);
            return groupInfoMapper.selectByExample(groupInfoExample);
        });
    }

}
