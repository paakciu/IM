package top.paakciu.core;

import top.paakciu.client.handler.LoginResponseHandler;
import top.paakciu.client.handler.RegisterResponseHandler;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.client.DefaultClient;
import top.paakciu.client.manage.NormalMessageManage;
//import top.paakciu.client.listener.PaakciuFuture;

public interface Client {
    //TODO 这个后面的返回值要改成Client，做个泛化，现在就不用提前优化了就这样先，方便测试，就不用一直改这个接口
    static DefaultClient defaultClient= DefaultClient.INSTANCE;

    //初始化
    Client initClienConnection(String host,int port);
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
