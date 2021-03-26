package top.paakciu.core;

import top.paakciu.client.ClientEventListener;
import top.paakciu.client.DefaultClient;
import top.paakciu.client.PaakciuFuture;
//import top.paakciu.client.PaakciuFuture;

public interface Client {
    static Client defaultClient= DefaultClient.INSTANCE;

    //初始化
    Client initClienConnection();
    //void startConnection(String host,int port);
    //注册
    PaakciuFuture register(String username, String passwrod);
    //登录
    PaakciuFuture login(String username,String passwrod);
    //发送消息
    Client send();
    //监听器
    public Client setEventListener(ClientEventListener clientEventListener);
    //token

    //。。。
}
