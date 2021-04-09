package top.paakciu.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.paakciu.client.handler.*;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.client.listener.ErrorListener;
import top.paakciu.client.manage.*;
import top.paakciu.config.IMConfig;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.protocal.packet.MessageRequestPacket;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

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
    private volatile NormalMessageManage normalMessageManage=null;
    @Override
    public NormalMessageManage getNormalMessageManage(){
        //尝试获取这个对象并且返回;
        MessageResponseHandler handler=nettyClient.channel.pipeline().get(MessageResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(MessageResponseHandler.class);
            //双重锁检测
            if (normalMessageManage == null) {
                synchronized (this) {
                    if (normalMessageManage == null) {
                        normalMessageManage = new NormalMessageManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return normalMessageManage;
    }
    private volatile CreateGroupManage createGroupManage=null;
    public CreateGroupManage getCreateGroupManage(){
        //尝试获取这个对象并且返回;
        CreateGroupResponseHandler handler=nettyClient.channel.pipeline().get(CreateGroupResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(CreateGroupResponseHandler.class);
            //双重锁检测
            if (createGroupManage == null) {
                synchronized (this) {
                    if (createGroupManage == null) {
                        createGroupManage = new CreateGroupManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return createGroupManage;
    }

    
    //JoinGroupManage
    //joinGroupManage
    //JoinGroupManageResponseHandler
    private volatile JoinGroupManage joinGroupManage=null;
    public JoinGroupManage getJoinGroupManage(){
        //尝试获取这个对象并且返回;
        JoinGroupResponseHandler handler=nettyClient.channel.pipeline().get(JoinGroupResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(JoinGroupResponseHandler.class);
            //双重锁检测
            if (joinGroupManage == null) {
                synchronized (this) {
                    if (joinGroupManage == null) {
                        joinGroupManage = new JoinGroupManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return joinGroupManage;
    }
    //QuitGroupManage
    //quitGroupManage
    //QuitGroupResponseHandler
    private volatile QuitGroupManage quitGroupManage=null;
    public QuitGroupManage getQuitGroupManage(){
        //尝试获取这个对象并且返回;
        QuitGroupResponseHandler handler=nettyClient.channel.pipeline().get(QuitGroupResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(QuitGroupResponseHandler.class);
            //双重锁检测
            if (quitGroupManage == null) {
                synchronized (this) {
                    if (quitGroupManage == null) {
                        quitGroupManage = new QuitGroupManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return quitGroupManage;
    }
    public ErrorMessageHandler setErrorListener(ErrorListener<ErrorMessagePacket> listener){
        ErrorMessageHandler handler=nettyClient.channel.pipeline().get(ErrorMessageHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(ErrorMessageHandler.class);
            handler.setListener(listener);
        }
        return handler;
    }
    //GetGroupMembersManage
    //getGroupMembersManage
    //GetGroupMembersResponseHandler
    private volatile GetGroupMembersManage getGroupMembersManage=null;
    public GetGroupMembersManage getGetGroupMembersManage(){
        //尝试获取这个对象并且返回;
        GetGroupMembersResponseHandler handler=nettyClient.channel.pipeline().get(GetGroupMembersResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(GetGroupMembersResponseHandler.class);
            //双重锁检测
            if (getGroupMembersManage == null) {
                synchronized (this) {
                    if (getGroupMembersManage == null) {
                        getGroupMembersManage = new GetGroupMembersManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return getGroupMembersManage;
    }
    
    
    
//    private ConcurrentHashMap<Class,Object> manageMap=new ConcurrentHashMap<Class,Object>();
//    public <manage, hand> manage getManage(Class<manage> manageClazz, Class<? extends ChannelHandler> handlerClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        //尝试获取这个对象并且返回;
//        ChannelHandler handler=nettyClient.channel.pipeline().get(handlerClass);
//        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
//            if(handler==null)
//                handler=nettyClient.channel.pipeline().get(handlerClass);
//            //双重锁检测
//            if (manageMap.get(manageClazz) == null) {
//                synchronized (this) {
//                    if (manageMap.get(manageClazz) == null) {
//                        manage temp=manageClazz.getConstructor(Channel.class,ChannelHandler.class).newInstance(nettyClient.channel,handler);
//                        manageMap.put(manageClazz,temp);
//                        //quitGroupManage = new QuitGroupManage(nettyClient.channel,handler);
//                    }
//                }
//            }
//        }
//        return (manage) manageMap.get(manageClazz);
//    }
    
    @Override
    public Client setEventListener(ClientEventListener clientEventListener) {
        nettyClient.setClientEventListener(clientEventListener);
        return this;
    }
    @Override
    public Client logout(){
        nettyClient.channel.close();
        return this;
    }

} 
