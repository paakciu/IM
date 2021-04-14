package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupInfoExample;

public interface GroupInfoMapper {
    long countByExample(GroupInfoExample example);

    int deleteByExample(GroupInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    List<GroupInfo> selectByExampleWithRowbounds(GroupInfoExample example, RowBounds rowBounds);

    List<GroupInfo> selectByExample(GroupInfoExample example);

    GroupInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByExample(@Param("record") GroupInfo record, @Param("example") GroupInfoExample example);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);
}