package top.paakciu.core;

import io.netty.channel.Channel;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.client.listener.ResponseListener;
import top.paakciu.client.handler.LoginResponseHandler;
import top.paakciu.protocal.packet.JoinGroupRequestPacket;
import top.paakciu.protocal.packet.JoinGroupResponsePacket;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author paakciu
 * @ClassName: test
 * @date: 2021/3/18 16:27
 */
public class test implements ClientEventListener {

    static final Client CLIENT=Client.defaultClient;
    public static void main(String[] args) {

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
                //System.out.println("连接成功s");
                //register();
                Scanner sc=new Scanner(System.in);
                System.out.println("请输入登录的账号密码：");
                String name=sc.nextLine();
                String psw=sc.nextLine();
                login(name,psw);
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
         * 链式调用！
         */
        Client.defaultClient.register("username1","password1")
                .setSendFailListener(()-> {
                    //TODO 发送服务器失败要做的事
                    System.out.println("发送至服务器失败");
                })
                .setSendSuccessListener(()->{
                    //TODO 发送服务器成功要做的事
                    System.out.println("发送至服务器成功");
                })
                .setFailListener(str -> {
                    //TODO 服务器返回结果为失败,str为返回失败的消息
                    System.out.println(str);
                })
                .setSuccessListener(str -> {
                    //TODO 服务器返回结果为成功,str为返回成功的消息
                    System.out.println(str);

                });


    }
    public void login(String username,String password) {
        Client.defaultClient.login(username,password)
                .setSendFailListener(()-> {
                    //TODO 发送服务器失败要做的事
                    System.out.println("发送至服务器失败");
                })
                .setSendSuccessListener(()->{
                    //TODO 发送服务器成功要做的事
                    System.out.println("发送至服务器成功");
                })
                .setFailListener(str -> {
                    //TODO 服务器返回结果为失败,str为返回失败的原因-如“账号密码校验失败”
                    System.out.println(str);
                })
                .setSuccessListener(channelUser -> {
                    //TODO 服务器返回结果为成功,str为返回成功 channelUser对象，包括惟一标识号id，和账号名
                    System.out.println(channelUser);
                    System.out.println("id="+channelUser.getUserId());

                    new Thread(()->{
                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            System.out.println("请输入toid msg：");
                            Long toid = sc.nextLong();
                            String msg = sc.next();
                            send(toid,msg);
                        }
                    }).start();

                })
        ;

    }
    public void logout(){
        Client.defaultClient.logout();
    }
    public void setSendListener(){
        Client.defaultClient.getNormalMessageManage()
                //收到消息的处理器
                .setHandlerListener(messageResponsePacket->{
                    //TODO 这里写怎么处理这些数据的，下面是处理样例
                    System.out.println(messageResponsePacket.getDate()
                            + ": 收到服务端的消息: "
                            + messageResponsePacket.getMessage());
                    Long fromUserId = messageResponsePacket.getFromUserId();
                    String fromUserName = messageResponsePacket.getFromUserName();
                    System.out.println(fromUserId + ":"
                            + fromUserName + " -> "
                            + messageResponsePacket .getMessage());
                })
                //发送到服务器成功
                .setSendSuccessListener(()->{
                    System.out.println("发送到服务器成功");
                })
                //发送到服务器失败
                .setSendFailListener(()->{
                    System.out.println("发送到服务器失败");
                });
    }
    public void send(Long toid,String msg){
        Client.defaultClient.getNormalMessageManage().send(toid,msg);
    }

    public void setCreateGroupListener(){
        Client.defaultClient.getCreateGroupManage().setSuccessListener((createGroupResponsePacket)->{
            System.out.println("群创建成功，群里面有："+createGroupResponsePacket.getUserNameList());
        });
    }
    public void CreateGroupListener(List<Long> userList) {
        Client.defaultClient.getCreateGroupManage().createGroup(userList);
    }
    public void setJoinGroupListener(){
        Client.defaultClient.getJoinGroupManage()
                .setSuccessListener((joinGroupResponsePacket)->{
                    System.out.println("加入群[" + joinGroupResponsePacket.getGroupId() + "]成功!");
                })
                .setFailListener(JoinGroupResponsePacket->{
                    System.err.println("加入群[" + JoinGroupResponsePacket.getGroupId() + "]失败，原因为：" + JoinGroupResponsePacket.getReason());
                });
    }
    public void joinGroup(Long groupId){
        Client.defaultClient.getJoinGroupManage().joinGroup(groupId);
    }
    public void setQuitGroup(){
        Client.defaultClient.getQuitGroupManage()
                .setSuccessListener((responsePacket)->{
                    System.out.println("退出群聊[" + responsePacket.getGroupId() + "]成功！");
                })
                .setFailListener((responsePacket)->{
                    System.out.println("退出群聊[" + responsePacket.getGroupId() + "]失败！");
                });
    }
    public void quitGroup(Long groupid){
        Client.defaultClient.getQuitGroupManage().quitGroup(groupid);
    }

    public void setErrorListener(){
        Client.defaultClient.setErrorListener((errorMessagePacket)->{
            System.out.println("收到出问题的消息"+errorMessagePacket.getReason()+errorMessagePacket.getErrorCode());
        });
    }

    public void setGetGroupmemeberslistener(){
        Client.defaultClient.getGetGroupMembersManage().setHandlerListener(getGroupMembersResponsePacket->{
            System.out.println("群[" + getGroupMembersResponsePacket.getGroupId() + "]中的人包括：" + getGroupMembersResponsePacket.getChannelUserList());
        });
    }
    public void getgroupmemebers(Long groupid){
        Client.defaultClient.getGetGroupMembersManage().getGroupMembers(groupid);
    }
}


//TEMPMANAGE
//tEMPMANAGE
//TRESPONHANDLER
//    private volatile TEMPMANAGE tEMPMANAGE=null;
//    public TEMPMANAGE getTEMPMANAGE(){
//        //尝试获取这个对象并且返回;
//        TRESPONHANDLER handler=nettyClient.channel.pipeline().get(TRESPONHANDLER.class);
//        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
//            if(handler==null)
//                handler=nettyClient.channel.pipeline().get(TRESPONHANDLER.class);
//            //双重锁检测
//            if (tEMPMANAGE == null) {
//                synchronized (this) {
//                    if (tEMPMANAGE == null) {
//                        tEMPMANAGE = new TEMPMANAGE(nettyClient.channel,handler);
//                    }
//                }
//            }
//        }
//        return tEMPMANAGE;
//    }