package top.paakciu.core;

import org.apache.ibatis.exceptions.PersistenceException;
import top.paakciu.service.UserService;

import java.io.IOException;

/**
 * @author paakciu
 * @ClassName: test
 * @date: 2021/3/18 16:27
 */
public class test {
    public static void main(String[] args) {
        Integer ref=UserService.register("nihaxo","haha");
        if(ref!=null){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
        System.out.println();
//        Client client=DefaultClient.INSTANCE;
//        client.initClienConnection();
    }
}
