package top.paakciu.core;

import top.paakciu.client.NettyClient;
import top.paakciu.client.ClientEventListener;
import top.paakciu.config.IMConfig;
import top.paakciu.protocal.packet.RegisterRequestPacket;

/**
 * @author paakciu
 * @ClassName: DefaultClient
 * @date: 2021/3/19 18:49
 */
public class DefaultClient implements Client{

    public static final DefaultClient INSTANCE=new DefaultClient();
    private NettyClient nettyClient=new NettyClient();
    private boolean channelIsOK=false;


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
    public Client register(String username,String passwrod) {
        if(nettyClient.channelisOK&&nettyClient.channel!=null)
        {
            new Thread(() -> {
                RegisterRequestPacket requestPacket = new RegisterRequestPacket(username, passwrod);
                nettyClient.channel.writeAndFlush(requestPacket).addListeners((future)->{

                });
            }).start();
        }
        return this;
    }

    @Override
    public Client login(String username,String passwrod) {
        return this;
    }
    
    @Override
    public Client send() {

        return this;
    }
    
    @Override
    public Client setEventListener(ClientEventListener clientEventListener) {
        nettyClient.setClientEventListener(clientEventListener);
        return this;
    }
} 
