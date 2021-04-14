package top.paakciu.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import top.paakciu.mbg.mapper.UserMapper;
import top.paakciu.mbg.model.User;
import top.paakciu.mbg.model.UserExample;
import top.paakciu.utils.Sqlutils;

import java.io.IOException;
import java.util.List;

/**
 * @author paakciu
 * @ClassName: UserService
 * @date: 2021/3/18 15:28
 */
public class UserService {

    /**
     * 注册
     * @param username
     * @param password
     * @return 如果返回null，则注册失败
     */
    public static Integer register(String username,String password) {
        return Sqlutils.startSqlSession(UserMapper.class,(mapper)->{
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            return mapper.insert(user);
        });
    }

    public static List<User> getAllUser(){
        //这个方法一般不要使用，太多了，不能直接返回全部
        return Sqlutils.startSqlSession(UserMapper.class,(mapper)->{
            return mapper.selectByExample(new UserExample());
        });
    }
    public static List<User> getUserListByIdList(List<Long> UserIdList){
        return Sqlutils.startSqlSession(UserMapper.class,mapper -> {
           UserExample example=new UserExample();
           example.createCriteria().andIdIn(UserIdList);
           return mapper.selectByExample(example);
        });
    }
    public static User getUserById(long id) {
        return Sqlutils.startSqlSession(UserMapper.class,mapper -> {
//            UserExample userExample=new UserExample();
//            userExample.createCriteria().andIdEqualTo(id);
            User user=mapper.selectByPrimaryKey(id);

            return user;
        });
    }

    public static User getUserByUserNameAndPassword(String userName,String password){
        return Sqlutils.startSqlSession(UserMapper.class,mapper -> {
            UserExample userExample=new UserExample();
            userExample.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(password);
            List<User> list=mapper.selectByExample(userExample);

            if(list.size()==0)
                return null;
            else
                return list.get(0);
        });
    }
}
