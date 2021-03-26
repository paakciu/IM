package top.paakciu.client;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import top.paakciu.client.handler.RegisterResponseHandler;
import top.paakciu.config.IMConfig;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.RegisterRequestPacket;

/**
 * @author paakciu
 * @ClassName: DefaultClient
 * @date: 2021/3/19 18:49
 */
public class DefaultClient implements Client {

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
    public PaakciuFuture register(String username,String passwrod) {
        PaakciuFuture paakciuFuture=null;
        if(nettyClient.channelisOK&&nettyClient.channel!=null)
        {
            RegisterRequestPacket requestPacket = new RegisterRequestPacket(username, passwrod);
            ChannelFuture future=nettyClient.channel.writeAndFlush(requestPacket);
            paakciuFuture=new PaakciuFuture(future);
            RegisterResponseHandler.INSTANCE.setFuture(paakciuFuture);
        }

        return paakciuFuture;
    }

    @Override
    public PaakciuFuture login(String username,String passwrod) {
        PaakciuFuture paakciuFuture=null;
        if(nettyClient.channelisOK&&nettyClient.channel!=null)
        {
            RegisterRequestPacket requestPacket = new RegisterRequestPacket(username, passwrod);
            ChannelFuture future=nettyClient.channel.writeAndFlush(requestPacket);
            paakciuFuture=new PaakciuFuture(future);
            RegisterResponseHandler.INSTANCE.setFuture(paakciuFuture);
        }

        return paakciuFuture;
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
