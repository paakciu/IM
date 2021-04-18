package top.paakciu.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import top.paakciu.client.handler.*;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.client.listener.ErrorListener;
import top.paakciu.client.listener.SendSuccessListener;
import top.paakciu.client.manage.*;
import top.paakciu.config.IMConfig;
import top.paakciu.core.Client;
import top.paakciu.protocal.packet.ErrorMessagePacket;
import top.paakciu.utils.AttributesHelper;
import top.paakciu.utils.info.ChannelUser;

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

    public ChannelUser getChannelUser(){
        return AttributesHelper.getChannelUser(getChannel());
    }
    public Channel getChannel(){
        return this.nettyClient.channel;
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (normalMessageManage == null&&handler!=null) {
                synchronized (this) {
                    if (normalMessageManage == null&&handler!=null) {
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (createGroupManage == null&&handler!=null) {
                synchronized (this) {
                    if (createGroupManage == null&&handler!=null) {
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (joinGroupManage == null&&handler!=null) {
                synchronized (this) {
                    if (joinGroupManage == null&&handler!=null) {
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (quitGroupManage == null&&handler!=null) {
                synchronized (this) {
                    if (quitGroupManage == null&&handler!=null) {
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
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
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (getGroupMembersManage == null&&handler!=null) {
                synchronized (this) {
                    if (getGroupMembersManage == null&&handler!=null) {
                        getGroupMembersManage = new GetGroupMembersManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return getGroupMembersManage;
    }
    private volatile GetOffLineMessageManage getOffLineMessageManage=null;
    public GetOffLineMessageManage getGetOffLineMessageManage(){
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            //双重锁检测
            if (getOffLineMessageManage == null) {
                synchronized (this) {
                    if (getOffLineMessageManage == null) {
                        getOffLineMessageManage = new GetOffLineMessageManage(nettyClient.channel);
                    }
                }
            }
        }
        return getOffLineMessageManage;
    }
    private volatile PullMessageManage pullMessageManage=null;
    public PullMessageManage getPullMessageManage(){
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            //双重锁检测
            if (pullMessageManage == null) {
                synchronized (this) {
                    if (pullMessageManage == null) {
                        pullMessageManage = new PullMessageManage(nettyClient.channel);
                    }
                }
            }
        }
        return pullMessageManage;
    }

    //ExtraManage
    //extraManage
    //ExtraResponseHandler
    private volatile ExtraManage extraManage=null;
    public ExtraManage getExtraManage(){
        //尝试获取这个对象-双校验;
        ExtraResponseHandler handler=nettyClient.channel.pipeline().get(ExtraResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(ExtraResponseHandler.class);
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (extraManage == null&&handler!=null) {
                synchronized (this) {
                    if (extraManage == null&&handler!=null) {
                        extraManage = new ExtraManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return extraManage;
    }
    //GetGroupListManage
    //getGroupListResponseManage
    //GetGroupListResponseHandler
    private volatile GetGroupListManage getGroupListResponseManage=null;
    public GetGroupListManage getGetGroupListResponseManage(){
        //尝试获取这个对象-双校验;
        GetGroupListResponseHandler handler=nettyClient.channel.pipeline().get(GetGroupListResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(GetGroupListResponseHandler.class);
            if(handler==null)
                System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (getGroupListResponseManage == null&&handler!=null) {
                synchronized (this) {
                    if (getGroupListResponseManage == null&&handler!=null) {
                        getGroupListResponseManage = new GetGroupListManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return getGroupListResponseManage;
    }
    
    //GroupMessageManage
    //groupMessageManage
    //GroupMessageResponseHandler
    private volatile GroupMessageManage groupMessageManage=null;
    public GroupMessageManage getGroupMessageManage(){
        //尝试获取这个对象-双校验;
        GroupMessageResponseHandler handler=nettyClient.channel.pipeline().get(GroupMessageResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(GroupMessageResponseHandler.class);
            if(handler==null)
                    System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (groupMessageManage == null&&handler!=null) {
                synchronized (this) {
                    if (groupMessageManage == null&&handler!=null) {
                        groupMessageManage = new GroupMessageManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return groupMessageManage;
    }
    //OffLineGroupMessageManage
    //offLineGroupMessageManage
    //OffLineGroupMessageResponseHandler
    private volatile OffLineGroupMessageManage offLineGroupMessageManage=null;
    public OffLineGroupMessageManage getOffLineGroupMessageManage(){
        //尝试获取这个对象-双校验;
        OffLineGroupMessageResponseHandler handler=nettyClient.channel.pipeline().get(OffLineGroupMessageResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(OffLineGroupMessageResponseHandler.class);
            if(handler==null)
                    System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (offLineGroupMessageManage == null&&handler!=null) {
                synchronized (this) {
                    if (offLineGroupMessageManage == null&&handler!=null) {
                        offLineGroupMessageManage = new OffLineGroupMessageManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return offLineGroupMessageManage;
    }


    //ExtraGroupManage
    //extraGroupManage
    //ExtraGroupResponseHandler
    private volatile ExtraGroupManage extraGroupManage=null;
    public ExtraGroupManage getExtraGroupManage(){
        //尝试获取这个对象-双校验;
        ExtraGroupResponseHandler handler=nettyClient.channel.pipeline().get(ExtraGroupResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(ExtraGroupResponseHandler.class);
            if(handler==null)
                    System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (extraGroupManage == null&&handler!=null) {
                synchronized (this) {
                    if (extraGroupManage == null&&handler!=null) {
                        extraGroupManage = new ExtraGroupManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return extraGroupManage;
    }

    //GetInfoAndFriendsManage
    //GetInfoAndFriendsManage
    //getInfoAndFriendsManage
    //GetInfoAndFriendResponseHandler
    private volatile GetInfoAndFriendsManage getInfoAndFriendsManage=null;
    public GetInfoAndFriendsManage getGetInfoAndFriendsManage(){
        //尝试获取这个对象-双校验;
        GetInfoAndFriendResponseHandler handler=nettyClient.channel.pipeline().get(GetInfoAndFriendResponseHandler.class);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if(handler==null)
                handler=nettyClient.channel.pipeline().get(GetInfoAndFriendResponseHandler.class);
            if(handler==null)
                    System.err.println("请检查NettyClient是否添加该handler");
            //双重锁检测
            if (getInfoAndFriendsManage == null&&handler!=null) {
                synchronized (this) {
                    if (getInfoAndFriendsManage == null&&handler!=null) {
                        getInfoAndFriendsManage = new GetInfoAndFriendsManage(nettyClient.channel,handler);
                    }
                }
            }
        }
        return getInfoAndFriendsManage;
    }
    

    public <handler extends ChannelHandler> handler getManage(Class<handler> handlerClazz, SendSuccessListener listener){
        handler handler=nettyClient.channel.pipeline().get(handlerClazz);
        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
            if (handler == null)
                handler = nettyClient.channel.pipeline().get(handlerClazz);
            //双重锁检测
            if(listener!=null)
                listener.onSendSuccess();
        }
        return handler;
    }

    
    
//    private ConcurrentHashMap<Class,Object> manageMap=new ConcurrentHashMap<Class,Object>();
//    public <manage, hand> manage getManage(Class<manage> manageClazz, Class<? extends ChannelHandler> handlerClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        //尝试获取这个对象并且返回;
//        ChannelHandler handler=nettyClient.channel.pipeline().get(handlerClass);
//        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
//            if(handler==null)
//                handler=nettyClient.channel.pipeline().get(handlerClass);
//            //双重锁检测
//            if (manageMap.get(manageClazz) == null&&handler!=null) {
//                synchronized (this) {
//                    if (manageMap.get(manageClazz) == null&&handler!=null) {
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
