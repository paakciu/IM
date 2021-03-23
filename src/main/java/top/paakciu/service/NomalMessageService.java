package top.paakciu.service;

import org.apache.ibatis.session.RowBounds;
import top.paakciu.mbg.mapper.NormalMsgMapper;
import top.paakciu.mbg.mapper.UserMapper;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgExample;
import top.paakciu.mbg.model.User;
import top.paakciu.utils.Sqlutils;

import java.util.Date;
import java.util.List;


/**
 * @author paakciu
 * @ClassName: NomalMessageService
 * @date: 2021/3/18 17:09
 */
public class NomalMessageService {

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
    public static List<NormalMsg> getMsg(int topnums)
    {
        //这个肯定是不能直接返回全部的
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            example.setOrderByClause("NM_id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(0,topnums));
        });
    }
    //这个找历史消息的关键咧
    public static List<NormalMsg> getMsgByToid(Long toid,int topnums)
    {
        return getMsgByToid(toid,0,topnums);
    }
    public static List<NormalMsg> getMsgByToid(Long toid,int offset,int topnums)
    {
        if(topnums>1000)
        {
            //要得太过分了，这里要做一个限制的,以防恶意攻击
            topnums=1000;
        }
        int finalTopnums = topnums;
        return Sqlutils.startSqlSession(NormalMsgMapper.class,(mapper)->{
            NormalMsgExample example=new NormalMsgExample();
            example.createCriteria().andNmToidEqualTo(toid);
            example.setOrderByClause("NM_id DESC");
            return mapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset, finalTopnums));
        });
    }

    public static void main(String[] args) {
//        for(int i=1;i<10;i++)
//        {
//            addNomalMessage(123L,321L,"msg"+i);
//        }
        for (NormalMsg normalMsg : getMsg(8)) {
            System.out.println(normalMsg);
        }
    }

}
