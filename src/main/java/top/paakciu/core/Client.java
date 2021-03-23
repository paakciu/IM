package top.paakciu.core;

import top.paakciu.client.ClientEventListener;

public interface Client {
    static Client defaultClient=DefaultClient.INSTANCE;

    //初始化
    Client initClienConnection();
    //void startConnection(String host,int port);
    //注册
    Client register(String username,String passwrod);
    //登录
    Client login(String username,String passwrod);
    //发送消息
    Client send();
    //监听器
    public Client setEventListener(ClientEventListener clientEventListener);
    //token

    //。。。
}
