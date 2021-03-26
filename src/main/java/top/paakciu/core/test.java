package top.paakciu.core;

import io.netty.channel.Channel;
import org.apache.ibatis.exceptions.PersistenceException;
import top.paakciu.client.ClientEventListener;
import top.paakciu.client.DefaultClient;
import top.paakciu.client.ResponseListener;
import top.paakciu.service.UserService;

import java.io.IOException;

/**
 * @author paakciu
 * @ClassName: test
 * @date: 2021/3/18 16:27
 */
public class test implements ClientEventListener {


    public static void main(String[] args) {
//        Integer ref=UserService.register("nihaxo","haha");
//        if(ref!=null){
//            System.out.println("注册成功");
//        }else{
//            System.out.println("注册失败");
//        }
//        System.out.println();
//        Client client=DefaultClient.INSTANCE;
//        client.initClienConnection();
        new test().onCreate();
    }
    public void onCreate()
    {
        /**
         * 初始化的方法lisntener里面的方法已经是异步方法了，不需要另外创建线程
         * 线程池参数可调节IMconfig文件中的CLIENT_THREAD_POOL_NUM参数
         * {@link top.paakciu.config.IMConfig}
         */
        Client.defaultClient.initClienConnection().setEventListener(new ClientEventListener() {
            @Override
            public void onInitChannel() {
                //TODO 初始化连接的时候要处理的事情

            }

            @Override
            public void onConnectSuccess(Channel channel) {
                //TODO 连接成功后要做的事情

            }

            @Override
            public void onConnectFail(int retry) {
                //TODO 连接失败后要做的事情

            }
        });
    }
    public void register(){
        /**
         * 要注意连接成功之后，才能调用该方法进行注册
         * 关于注册事件的回调会在Netty的异步线程中进行，不需要建立线程。
         */
        Client.defaultClient.register("username","password")
                .addListener(new ResponseListener() {
            @Override
            public void onSendSuccess() {
                //TODO 发送服务器成功要做的事
            }

            @Override
            public void onSendFail() {
                //TODO 发送服务器失败要做的事
            }

            @Override
            public void onSuccess(String str) {
                //TODO 服务器返回结果为成功
            }

            @Override
            public void onFail(String str) {
                //TODO 服务器返回结果为失败
            }
        });
    }
}
