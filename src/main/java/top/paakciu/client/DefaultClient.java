package top.paakciu.client;

import top.paakciu.client.handler.LoginResponseHandler;
import top.paakciu.client.handler.RegisterResponseHandler;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.config.IMConfig;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.MessageRequestPacket;

/**
 * @author paakciu
 * @ClassName: DefaultClient
 * @date: 2021/3/19 18:49
 */
public class DefaultClient implements Client {

    public static final DefaultClient INSTANCE=new DefaultClient();
    private NettyClient nettyClient=new NettyClient();
    //private boolean channelIsOK=false;


    //保证单例的话，就声明成private或者protect
    //暂定使用单例模式，一个客户端只使用一个IM就够了
    private DefaultClient() {
    }

    @Override
    public Client initClienConnection() {
        nettyClient.startConnection(IMConfig.HOST,IMConfig.PORT);
        return this;
    }
    

    @Override
    public RegisterResponseHandler register(String username, String passwrod) {
        //尝试获取这个对象并且返回;
        RegisterResponseHandler handler=nettyClient.channel.pipeline().get(RegisterResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null)
        {
            //修复运行时获取
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(RegisterResponseHandler.class);
            handler.regiter(this,nettyClient.channel,username,passwrod);
        }
        return handler;
    }

    @Override
    public LoginResponseHandler login(String username, String passwrod) {
        //尝试获取这个对象并且返回;
        LoginResponseHandler handler=nettyClient.channel.pipeline().get(LoginResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null)
        {
            //修复运行时获取
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(LoginResponseHandler.class);
            handler.login(this,nettyClient.channel,username,passwrod);
        }
        return handler;
    }
    
    @Override
    public Client send(Long toId, String msg) {
        MessageRequestPacket messageRequestPacket=new MessageRequestPacket();
        messageRequestPacket.setToUserId(toId);
        messageRequestPacket.setMessage(msg);
        nettyClient.channel.writeAndFlush(messageRequestPacket);
        return this;
    }
    
    @Override
    public Client setEventListener(ClientEventListener clientEventListener) {
        nettyClient.setClientEventListener(clientEventListener);
        return this;
    }
    public Client logout(){
        nettyClient.channel.close();
        return this;
    }
} 