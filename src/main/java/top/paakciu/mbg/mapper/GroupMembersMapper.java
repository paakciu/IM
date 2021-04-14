package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.GroupMembers;
import top.paakciu.mbg.model.GroupMembersExample;

public interface GroupMembersMapper {
    long countByExample(GroupMembersExample example);

    int deleteByExample(GroupMembersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMembers record);

    int insertSelective(GroupMembers record);

    List<GroupMembers> selectByExampleWithRowbounds(GroupMembersExample example, RowBounds rowBounds);

    List<GroupMembers> selectByExample(GroupMembersExample example);

    GroupMembers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMembers record, @Param("example") GroupMembersExample example);

    int updateByExample(@Param("record") GroupMembers record, @Param("example") GroupMembersExample example);

    int updateByPrimaryKeySelective(GroupMembers record);

    int updateByPrimaryKey(GroupMembers record);
}