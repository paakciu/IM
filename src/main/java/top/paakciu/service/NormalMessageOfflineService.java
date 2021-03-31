package top.paakciu.service;

import top.paakciu.mbg.mapper.NormalMsgMapper;
import top.paakciu.mbg.mapper.NormalMsgOfflineMapper;
import top.paakciu.mbg.model.NormalMsg;
import top.paakciu.mbg.model.NormalMsgOffline;
import top.paakciu.utils.Sqlutils;

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
}
