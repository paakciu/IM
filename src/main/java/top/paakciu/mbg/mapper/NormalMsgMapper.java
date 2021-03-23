package top.paakciu.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgExample;

public interface NormalMsgMapper {
    long countByExample(NormalMsgExample example);

    int deleteByExample(NormalMsgExample example);

    int deleteByPrimaryKey(Long nmId);

    int insert(NormalMsg record);

    int insertSelective(NormalMsg record);

    List<NormalMsg> selectByExampleWithBLOBsWithRowbounds(NormalMsgExample example, RowBounds rowBounds);

    List<NormalMsg> selectByExampleWithBLOBs(NormalMsgExample example);

    List<NormalMsg> selectByExampleWithRowbounds(NormalMsgExample example, RowBounds rowBounds);

    List<NormalMsg> selectByExample(NormalMsgExample example);

    NormalMsg selectByPrimaryKey(Long nmId);

    int updateByExampleSelective(@Param("record") NormalMsg record, @Param("example") NormalMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") NormalMsg record, @Param("example") NormalMsgExample example);

    int updateByExample(@Param("record") NormalMsg record, @Param("example") NormalMsgExample example);

    int updateByPrimaryKeySelective(NormalMsg record);

    int updateByPrimaryKeyWithBLOBs(NormalMsg record);

    int updateByPrimaryKey(NormalMsg record);
}