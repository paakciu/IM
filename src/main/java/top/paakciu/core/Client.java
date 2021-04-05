package top.paakciu.core;

import top.paakciu.client.handler.LoginResponseHandler;
import top.paakciu.client.handler.RegisterResponseHandler;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.client.DefaultClient;
import top.paakciu.client.manage.NormalMessageManage;
//import top.paakciu.client.listener.PaakciuFuture;

public interface Client {
    static Client defaultClient= DefaultClient.INSTANCE;

    //初始化
    Client initClienConnection();
    //void startConnection(String host,int port);
    //注册
    RegisterResponseHandler register(String username, String passwrod);
    //登录
    LoginResponseHandler login(String username, String passwrod);
    //发送消息
    NormalMessageManage getNormalMessageManage();
    Client logout();

    //监听器
    Client setEventListener(ClientEventListener clientEventListener);


    //token

    //。。。
}
