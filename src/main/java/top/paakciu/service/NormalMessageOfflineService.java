package top.paakciu.service;

import top.paakciu.mbg.mapper.NormalMsgMapper;
import top.paakciu.mbg.mapper.NormalMsgOfflineMapper;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgExample;
import top.paakciu.mbg.model.NormalMsgOffline;
import top.paakciu.mbg.model.NormalMsgOfflineExample;
import top.paakciu.utils.Sqlutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: NormalMessageOfflineService
 * @date: 2021/3/31 15:19
 */
public class NormalMessageOfflineService {

    public static int addNomalMessage(NormalMsgOffline msg){
        return Sqlutils.startSqlSession(NormalMsgOfflineMapper.class,(mapper)->{
            return mapper.insert(msg);
        });
    }

    public static List<NormalMsg> getNormalMsgOfflineByid(Long id){
        return Sqlutils.startSqlSessionByListener((sqlSession)->{
            NormalMsgOfflineMapper normalMsgOfflineMapper=sqlSession.getMapper(NormalMsgOfflineMapper.class);
            NormalMsgMapper normalMsgMapper=sqlSession.getMapper(NormalMsgMapper.class);

            NormalMsgOfflineExample offlineExample=new NormalMsgOfflineExample();
            offlineExample.createCriteria().andNmoToidEqualTo(id);//.andNmoStateEqualTo((byte) 3);//离线状态
            List<NormalMsgOffline> offlist=normalMsgOfflineMapper.selectByExample(offlineExample);

            List<NormalMsg> list=new ArrayList<NormalMsg>();
            for (NormalMsgOffline msgOffline : offlist) {
                NormalMsg tempmsg=normalMsgMapper.selectByPrimaryKey(msgOffline.getMsgid());
                //只有搜索一个出来的是正确的
                list.add(tempmsg);
//                msgOffline.setNmoState((byte)1);//已经投妥
//                normalMsgOfflineMapper.updateByPrimaryKeySelective(msgOffline);
            }
            normalMsgOfflineMapper.deleteByExample(offlineExample);

            return list;
        });
    }
}
