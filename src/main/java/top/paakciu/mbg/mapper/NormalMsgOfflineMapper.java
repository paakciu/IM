package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.NormalMsgOffline;
import top.paakciu.mbg.model.NormalMsgOfflineExample;

public interface NormalMsgOfflineMapper {
    long countByExample(NormalMsgOfflineExample example);

    int deleteByExample(NormalMsgOfflineExample example);

    int deleteByPrimaryKey(Long nmoId);

    int insert(NormalMsgOffline record);

    int insertSelective(NormalMsgOffline record);

    List<NormalMsgOffline> selectByExampleWithRowbounds(NormalMsgOfflineExample example, RowBounds rowBounds);

    List<NormalMsgOffline> selectByExample(NormalMsgOfflineExample example);

    NormalMsgOffline selectByPrimaryKey(Long nmoId);

    int updateByExampleSelective(@Param("record") NormalMsgOffline record, @Param("example") NormalMsgOfflineExample example);

    int updateByExample(@Param("record") NormalMsgOffline record, @Param("example") NormalMsgOfflineExample example);

    int updateByPrimaryKeySelective(NormalMsgOffline record);

    int updateByPrimaryKey(NormalMsgOffline record);
}