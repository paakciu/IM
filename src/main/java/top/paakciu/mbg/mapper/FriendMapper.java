package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.Friend;
import top.paakciu.mbg.model.FriendExample;

public interface FriendMapper {
    long countByExample(FriendExample example);

    int deleteByExample(FriendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Friend record);

    int insertSelective(Friend record);

    List<Friend> selectByExampleWithRowbounds(FriendExample example, RowBounds rowBounds);

    List<Friend> selectByExample(FriendExample example);

    Friend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);
}