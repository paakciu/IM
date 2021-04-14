package top.paakciu.service;

import top.paakciu.mbg.mapper.GroupInfoMapper;
import top.paakciu.mbg.mapper.GroupMembersMapper;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.GroupMembersExample;
import top.paakciu.utils.Sqlutils;

import java.util.List;

/**
 * @author paakciu
 * @ClassName: GroupMembersService
 * @date: 2021/4/13 13:58
 */
public class GroupMembersService {

    public static Integer addGroupMembers(Long groupId, List<Long> UserIdList){
        return Sqlutils.startSqlSession(GroupMembersMapper.class,mapper->{
            int ref=0;
            for (Long userid : UserIdList) {
                GroupMembers members=new GroupMembers();
                members.setGroupId(groupId);
                members.setGroupUserid(userid);

                GroupMembersExample example=new GroupMembersExample();
                example.createCriteria().andGroupIdEqualTo(groupId).andGroupUseridEqualTo(userid);
                List<GroupMembers> membersList=mapper.selectByExample(example);
                //不在群里才插入，在就不插入了
                if(membersList.size()==0)
                    ref+=mapper.insert(members);
            }
            return ref;
        });
    }
    public static Integer addGroupMember(Long groupId, Long userId){
        return Sqlutils.startSqlSession(GroupMembersMapper.class,mapper->{
            int ref=0;

            GroupMembers members=new GroupMembers();
            members.setGroupId(groupId);
            members.setGroupUserid(userId);

            GroupMembersExample example=new GroupMembersExample();
            example.createCriteria().andGroupIdEqualTo(groupId).andGroupUseridEqualTo(userId);
            List<GroupMembers> membersList=mapper.selectByExample(example);
            //不在群里才插入，在就不插入了
            if(membersList.size()==0)
                ref=mapper.insert(members);

            return ref;
        });
    }
    //根据群号获取群员消息!!
    public static List<GroupMembers> getGroupMembersByGroupId(long groupId){
        return Sqlutils.startSqlSession(GroupMembersMapper.class, mapper->{
            GroupMembersExample example=new GroupMembersExample();
            example.createCriteria().andGroupIdEqualTo(groupId);

            return mapper.selectByExample(example);
        });
    }
    public static List<GroupMembers> getGroupMembersByGroupIdAndUserId(long groupId,Long userId){
        return Sqlutils.startSqlSession(GroupMembersMapper.class, mapper->{
            GroupMembersExample example=new GroupMembersExample();
            example.createCriteria().andGroupIdEqualTo(groupId).andGroupUseridEqualTo(userId);
            return mapper.selectByExample(example);
        });
    }

    public static Integer deleteGroupMembersByGroupIdAndUserId(long groupId, Long userId){
        return Sqlutils.startSqlSessionByListener(sqlSession->{
            GroupMembersMapper groupMembersMapper=sqlSession.getMapper(GroupMembersMapper.class);

            //先查剩下的成员有多少个
//            GroupMembersExample example1 =new GroupMembersExample();
//            example1.createCriteria().andGroupIdEqualTo(groupId);
//            long nums=groupMembersMapper.countByExample(example1);
//            System.out.println("差出来剩下的成员有"+nums);

            GroupMembersExample example=new GroupMembersExample();
            example.createCriteria().andGroupIdEqualTo(groupId).andGroupUseridEqualTo(userId);
            int ref= groupMembersMapper.deleteByExample(example);

            //这里验证一下是否已经是最后一个人了，如果是最后一个人的话那就删除该群组！---想了想不需要了，因为不会从会员表中反向获取到该群组，故不需要
//            if(nums==1L){
//                GroupInfoMapper groupInfoMapper=sqlSession.getMapper(GroupInfoMapper.class);
//
//
//            }
            return ref;


        });
    }


}
