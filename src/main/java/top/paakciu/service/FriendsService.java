package top.paakciu.service;

import top.paakciu.mbg.mapper.FriendMapper;
import top.paakciu.mbg.model.Friend;
import top.paakciu.mbg.model.FriendExample;
import top.paakciu.utils.Sqlutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: FriendsService
 * @date: 2021/4/15 23:10
 */
public class FriendsService {

    public static Integer addFriends(Long id1,Long id2) {
        if(id1==id2)return 0;
        List<Long> list=new ArrayList<Long>();
        list.add(id1);list.add(id2);
        return Sqlutils.startSqlSession(FriendMapper.class,mapper->{

            FriendExample example=new FriendExample();
            example.createCriteria().andUseridIn(list).andFrienduseridIn(list);
            List<Friend> friendList= mapper.selectByExample(example);
            if(friendList!=null && friendList.size()>=2){
                //数据库已经有这两个好友了不添加
                return 0;
            }

            Integer ref=0;
            Friend friend1=new Friend();
            friend1.setFrienduserid(id1);
            friend1.setUserid(id2);

            Friend friend2=new Friend();
            friend2.setFrienduserid(id2);
            friend2.setUserid(id1);
            ref+=mapper.insert(friend1);
            ref+=mapper.insert(friend2);
            return ref;
        });
    }
    public static Integer deleteFriends(Long id1,Long id2){
        if(id1==id2)return 0;
        return Sqlutils.startSqlSession(FriendMapper.class,friendMapper -> {
            List<Long> values=new ArrayList<>();
            values.add(id1);values.add(id2);
            FriendExample example=new FriendExample();
            example.createCriteria().andUseridIn(values).andFrienduseridIn(values);
            return friendMapper.deleteByExample(example);
        });
    }

    public static List<Friend> getFriends(Long userId){
        if(userId==null)return null;
        return Sqlutils.startSqlSession(FriendMapper.class,mapper->{
            FriendExample example=new FriendExample();
            example.createCriteria().andUseridEqualTo(userId);
            return mapper.selectByExample(example);
        });
    }
}
