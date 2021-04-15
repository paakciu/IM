package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.GroupMsg;
import top.paakciu.mbg.model.GroupMsgExample;

public interface GroupMsgMapper {
    long countByExample(GroupMsgExample example);

    int deleteByExample(GroupMsgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMsg record);

    int insertSelective(GroupMsg record);

    List<GroupMsg> selectByExampleWithBLOBsWithRowbounds(GroupMsgExample example, RowBounds rowBounds);

    List<GroupMsg> selectByExampleWithBLOBs(GroupMsgExample example);

    List<GroupMsg> selectByExampleWithRowbounds(GroupMsgExample example, RowBounds rowBounds);

    List<GroupMsg> selectByExample(GroupMsgExample example);

    GroupMsg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMsg record, @Param("example") GroupMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupMsg record, @Param("example") GroupMsgExample example);

    int updateByExample(@Param("record") GroupMsg record, @Param("example") GroupMsgExample example);

    int updateByPrimaryKeySelective(GroupMsg record);

    int updateByPrimaryKeyWithBLOBs(GroupMsg record);

    int updateByPrimaryKey(GroupMsg record);
}