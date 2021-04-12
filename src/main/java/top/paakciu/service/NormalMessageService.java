package top.paakciu.service;

import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.mapper.NormalMsgMapper;
import top.paakciu.mbg.mapper.UserMapper;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgExample;
import top.paakciu.mbg.model.User;
import top.paakciu.utils.Sqlutils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static top.paakciu.config.IMConfig.SERVER_DB_PAGE_LIMIT;


/**
 * @author paakciu
 * @ClassName: NormalMessageService
 * @date: 2021/3/18 17:09
 */
//TODO 使用Rowbounds的话，是查询全部数据出来，再分页的，所以效率会很低！
public class NormalMessageService {

    //region 部分参数
    public static int addNomalMessage(Long fromid,Long toid,String message){
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsg msg=new NormalMsg();
            msg.setNmFromid(fromid);
            msg.setNmToid(toid);
            msg.setNmMsg(message);
            msg.setNmTime(new Date());
            return mapper.insert(msg);
        });
    }
    //endregion

    //正常使用的方式
    public static int addNomalMessage(NormalMsg msg){
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            return mapper.insert(msg);
        });
    }
    //region 全参插入
    /**
     * 全参插入
     */
    public static int addNomalMessage(Long fromid,Long toid,String message,Byte type,Byte state){
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsg msg=new NormalMsg();
            msg.setNmFromid(fromid);
            msg.setNmToid(toid);
            msg.setNmMsg(message);
            msg.setNmTime(new Date());
            msg.setNmType(type);
            msg.setNmState(state);
            return mapper.insert(msg);
        });
    }
    //endregion

    //region 取出数据库中的前nums条
    public static List<NormalMsg> getMsg(int topnums)
    {
        //这个肯定是不能直接返回全部的
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(0,topnums));
        });
    }
    //endregion

    //这个找历史消息的关键咧
    //region 多写了的取消息方法
    public static List<NormalMsg> getMsgByToid(Long toid,int topnums)
    {
        return getMsgByToid(toid,0,topnums);
    }
    public static List<NormalMsg> getMsgByToid(Long toid,int offset,int topnums)
    {
        if(topnums>SERVER_DB_PAGE_LIMIT)
        {
            //要得太过分了，这里要做一个限制的,以防恶意攻击
            topnums=SERVER_DB_PAGE_LIMIT;
        }
        int finalTopnums = topnums;
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            example.createCriteria().andNmToidEqualTo(toid);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset, finalTopnums));
        });
    }
    //endregion

    //setAsSingleByFromMessageId,id1 id2为对话的双方
    public static List<NormalMsg> getMsgByFromMessageId(Long fromMessageId,Long id1,Long id2,boolean isBigger){
        List<Long> valueList=new ArrayList<>();
        valueList.add(id1);
        valueList.add(id2);
        //TODO 这个很有可能获取到很大的数据库
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            NormalMsgExample.Criteria criteria = example.createCriteria();
            criteria.andNmFromidIn(valueList).andNmToidIn(valueList);
            if(isBigger)
                criteria.andIdGreaterThanOrEqualTo(fromMessageId);
            else
                criteria.andIdLessThanOrEqualTo(fromMessageId);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBs(example);
        });
    }
    //setAsSingleBySize(long Id1,long Id2,int size)
    public static List<NormalMsg> getMsgBySize(long id1,long id2,int size){
        return getMsgByOffsetAndSize(id1,id2,0,size);
    }
    public static List<NormalMsg> getMsgByOffsetAndSize(long id1,long id2,int offset,int size){
        List<Long> valueList=new ArrayList<>();
        valueList.add(id1);
        valueList.add(id2);
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            NormalMsgExample.Criteria criteria = example.createCriteria();
            criteria.andNmFromidIn(valueList).andNmToidIn(valueList);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset, size));
        });
    }
    //setAsSingleByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int Size)
    public static List<NormalMsg> getMsgByFromMessageIdAndSize(Long fromMessageId1,Long Id1,Long Id2,boolean Bigger,int size){
        List<Long> valueList=new ArrayList<>();
        valueList.add(Id1);
        valueList.add(Id2);
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            NormalMsgExample.Criteria criteria = example.createCriteria();
            criteria.andNmFromidIn(valueList).andNmToidIn(valueList);
            if(Bigger)
                criteria.andIdGreaterThanOrEqualTo(fromMessageId1);
            else
                criteria.andIdLessThanOrEqualTo(fromMessageId1);
            example.setOrderByClause("id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(0, size));
        });
    }

    //测试
    public static void main(String[] args) {
        List<NormalMsg> list=NormalMessageService.getMsgByFromMessageIdAndSize(40L,21L, 25L, false,10);

        for (NormalMsg normalMsg : list) {
            System.out.println(normalMsg);
        }
    }
}
