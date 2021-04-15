package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.GroupMsgOffline;
import top.paakciu.mbg.model.GroupMsgOfflineExample;

public interface GroupMsgOfflineMapper {
    long countByExample(GroupMsgOfflineExample example);

    int deleteByExample(GroupMsgOfflineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupMsgOffline record);

    int insertSelective(GroupMsgOffline record);

    List<GroupMsgOffline> selectByExampleWithRowbounds(GroupMsgOfflineExample example, RowBounds rowBounds);

    List<GroupMsgOffline> selectByExample(GroupMsgOfflineExample example);

    GroupMsgOffline selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupMsgOffline record, @Param("example") GroupMsgOfflineExample example);

    int updateByExample(@Param("record") GroupMsgOffline record, @Param("example") GroupMsgOfflineExample example);

    int updateByPrimaryKeySelective(GroupMsgOffline record);

    int updateByPrimaryKey(GroupMsgOffline record);
}