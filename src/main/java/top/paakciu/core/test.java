package top.paakciu.core;

import io.netty.channel.Channel;
import top.paakciu.client.listener.ClientEventListener;
import top.paakciu.config.IMConfig;
import top.paakciu.mbg.model.GroupInfo;
import top.paakciu.mbg.model.GroupMsgOffline;
import top.paakciu.utils.info.ChannelUser;
import top.paakciu.utils.ExtraPacketHelper;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author paakciu
 * @ClassName: test
 * @date: 2021/3/18 16:27
 */
public class test implements ClientEventListener {

    static final Client CLIENT=Client.defaultClient;
//    static ChannelUser this_channeluser=null;
    //static Channel this_channel=null;
    private void printFail(){System.out.println("发送到服务器失败");}
    private void printSuccess(){System.out.println("发送到服务器成功");}
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
        Client.defaultClient.initClienConnection(IMConfig.HOST,IMConfig.PORT).setEventListener(new ClientEventListener() {
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

                    //监听器
                    setSendListener();
                    setCreateGroupListener();
                    setJoinGroupListener();
                    setErrorListener();
                    setQuitGroup();
                    setGetGroupmemeberslistener();
                    setGetGroupListListener();
                    setGroupMessageListener();
                    setGroupOfflineMessageListener();
                    ExtraListAdd();
                    setExtraGroupMessageListener();
                    setGetInfoAndFriendsListener();

                    new Thread(()->{
                        Scanner sc = new Scanner(System.in);

                        //聊天模拟
                        while (true) {
                            System.out.println("请输入toid msg：");
                            Long toid = sc.nextLong();
                            String msg = sc.next();
                            send(toid,msg);

//                            System.out.println("请输入togroupid msg：");
//                            Long togroupid = sc.nextLong();
//                            String msg = sc.next();
//                            sendGroupMessage(togroupid,msg);
//                            System.out.println("请输入任意字符串，测试添加好友");
//                            Long id1=sc.nextLong();
//                            Long id2=sc.nextLong();
//                            deleteFriends(id1,id2);
//                            System.out.println("请输入任意字符串，测试获取好友列表");
//                            sc.nextLine();
//                            getAllFriends(channelUser.getUserId());
//                            System.out.println("请输入任意字符串，测试获取在线好友列表");
//                            sc.nextLine();
//                            getOnLineFriends(channelUser.getUserId());
                            //sendExtraGroupMessage();
//                            getGroupOfflineMessage();
//                            getGroupList(channelUser.getUserId());
                            //getGroupmemebers(2L);
                            //quitGroup(2L,channelUser.getUserId());


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
                    //TODO 消息处理
                    ChannelUser thisChannelUser=Client.defaultClient.getChannelUser();
                    if(thisChannelUser!=null)
                        if(thisChannelUser.getUserId()==messageResponsePacket.getFromUserId()){
                            //TODO 这里的处理是收到了自己发送的消息的处理，证明消息发送正确
                            //建议是本地发送不要马上显示，而是收到服务器返回的自己发送的这条消息后，再进行显示，这个过程一般不会很慢
                            //这样做的好处在于，后面离线消息的推送可以复用该消息处理通道
                            System.out.println("收到自己发送的消息！");
                        }

                    //TODO 这里写怎么处理这些数据的，下面是处理样例
                    System.out.println(messageResponsePacket.getDate()
                            + ": 收到服务端的消息 ID:"
                            + messageResponsePacket.getMessageId()
                            + "消息："
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
        //todo 注意不能发送给自己，如果发送给自己会返回发送到服务器失败SendFailListener
        Client.defaultClient.getNormalMessageManage().send(toid,msg);
    }
    public void pullSingleMessage(){
        //获取历史消息
        ChannelUser user=Client.defaultClient.getChannelUser();
        /**
         * 方式1
         * 参数说明：
         * msgid：从哪个消息开始获取
         * id1 id2 ：顺序不重要，能识别是谁跟谁的聊天就行了
         * isBigger：从msgid 往id号增大的方向获取，还是往减小的方向获取
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageSingleByFromMessageId(20L
                        , user.getUserId()
                        , 25L
                        , true)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);
        /**
         * 方式2
         * 推荐使用：一开始使用这个，获取前size条消息，后面使用方式3逐渐往前获取
         * 参数说明：
         * id1/id2 ：顺序不重要，能识别是谁跟谁的聊天就行了
         * size: 获取消息的数量
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageSingleBySize(user.getUserId(),25L,10)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);
        //方式3--推荐使用：先使用方式2，获取前size条，后根据获取的消息id逐渐往前获取
        /**
         * 方式3
         * 推荐使用：先使用方式2，获取前size条，后根据获取的消息id逐渐往前获取
         * 参数说明：
         * msgid：从哪个消息开始获取
         * id1/id2 ：顺序不重要，能识别是谁跟谁的聊天就行了
         * size: 获取消息的数量
         * isBigger：从msgid 往id号增大的方向获取，还是往减小的方向获取
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageSingleByFromMessageIdAndSize(40L, user.getUserId(), 25L,false,10)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);
    }

    public void pullGroupMessage(){
        /**
         * 方式1
         * 推荐使用：这个可以用于群组离线消息的拉取，因为群组的离线消息的处理跟普通消息不一样
         * 这是因为群组消息有可能非常庞大，按需获取即可！
         * 参数说明：
         * long fromMessageId：从哪个消息开始获取
         * long groupid:群的id
         * bool isBigger：从msgid 往id号增大的方向获取，还是往减小的方向获取
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageGroupByFromMessageId(0L,2L,true)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);

        /**
         * 方式2
         * 推荐使用：一开始使用这个，获取前size条消息，后面使用方式3逐渐往前获取
         * 参数说明：
         * long groupid:群的id
         * int size: 获取消息的数量
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageGroupBySize(2L,10)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);
        /**
         * 方式3
         * 推荐使用：先使用方式2，获取前size条，后根据获取的消息id逐渐往前获取
         * 参数说明：
         * long fromMessageId：从哪个消息开始获取
         * long groupid:群的id
         * int size: 获取消息的数量
         * bool isBigger：从msgid 往id号增大的方向获取，还是往减小的方向获取
         */
        Client.defaultClient.getPullMessageManage()
                .pullMessageGroupByFromMessageIdAndSize(0L,2L,true,2)
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail);
    }
    public void getOffLineMessage(){
        //登陆后拉取离线消息
        Client.defaultClient.getGetOffLineMessageManage()
                //发送拉取离线消息请求
                .getOffLineMessage()
                //按需设置回调函数
                .setSendFailListener(this::printFail)
                .setSendSuccessListener(this::printSuccess)
        ;
    }

    List<Class> list= new CopyOnWriteArrayList<Class>();
    public void ExtraListAdd(){
        if(!list.contains(test1.class))
            list.add(test1.class);
    }
    public void setExtraSingleListener(){
        Client.defaultClient.getExtraManage()
                //消息处理
                .setHandlerListener((msg)->{
                    //对应消息的处理，其中test1需要自己映射包！
                    if(list.get(msg.getType())==test1.class){
                        test1 test1= ExtraPacketHelper.getObject(test1.class,msg);
                        System.out.println(test1);
                    }
                })
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail)
                ;
    }
    public void sendExtraSingle(){
        test1 box=new test1();
        box.setStr("nihao");
        box.setB(true);
        box.setX(100);
        Client.defaultClient.getExtraManage()
                //传递好参数即可：接收方id，自定义消息体，消息类型type（int）
                .sendExtraMessage(25L,box,list.indexOf(test1.class));
    }

    public void setErrorListener(){
        Client.defaultClient.setErrorListener((errorMessagePacket)->{
            System.out.println("收到出问题的消息"+errorMessagePacket.getReason()+errorMessagePacket.getErrorCode());
        });
    }

    public void setCreateGroupListener(){
        Client.defaultClient.getCreateGroupManage()
                .setSuccessListener((createGroupResponsePacket)->{
                    Long groupid=createGroupResponsePacket.getGroupId();
                    System.out.println("群创建成功，群id:"+groupid+" 群名:"+createGroupResponsePacket.getGroupName()+" 群里面有："+createGroupResponsePacket.getUserNameList());
                    System.out.println("IDlist:"+createGroupResponsePacket.getUserIdList());
                })
                .setFailListener((createGroupResponsePacket)->{
                    System.out.println("群创建失败");
                })
                //发送到服务器成功
                .setSendSuccessListener(this::printSuccess)
                //发送到服务器失败
                .setSendFailListener(this::printFail);
    }
    public void CreateGroup(List<Long> userList,String groupName) {
        Client.defaultClient.getCreateGroupManage().createGroup(userList,groupName);
    }

    public void setJoinGroupListener(){
        Client.defaultClient.getJoinGroupManage()
                .setSuccessListener((joinGroupResponsePacket)->{
                    if (joinGroupResponsePacket.isNewJoin()&&joinGroupResponsePacket.isOnLine()) {
                        System.out.println(joinGroupResponsePacket.getUserName()+"[在线]新加入群[" + joinGroupResponsePacket.getGroupId() + "]成功!");
                    }
                    else if (joinGroupResponsePacket.isNewJoin()&&!joinGroupResponsePacket.isOnLine()) {
                        System.out.println(joinGroupResponsePacket.getUserName()+"[离线]新加入群[" + joinGroupResponsePacket.getGroupId() + "]成功!");
                    }
                    else if(!joinGroupResponsePacket.isNewJoin()&& joinGroupResponsePacket.isOnLine()){
                        System.out.println(joinGroupResponsePacket.getUserName()+"登录群[" + joinGroupResponsePacket.getGroupId() + "]成功!");
                    }
                })
                .setFailListener(JoinGroupResponsePacket->{
                    System.err.println("加入群[" + JoinGroupResponsePacket.getGroupId() + "]失败，原因为：" + JoinGroupResponsePacket.getReason());
                });
    }
    public void joinGroup(Long groupId,Long userId){
        Client.defaultClient.getJoinGroupManage().joinGroup(groupId,userId);
    }

    public void setQuitGroup(){
        Client.defaultClient.getQuitGroupManage()
                //监听
                .setSuccessListener((responsePacket)->{
                    System.out.println(responsePacket.getUserId()+"退出群聊[" + responsePacket.getGroupId() + "]成功！");
                })
                .setFailListener((responsePacket)->{
                    System.out.println(responsePacket.getUserId()+"退出群聊[" + responsePacket.getGroupId() + "]失败！");
                });
            }
    public void quitGroup(Long groupid,Long userId){
        Client.defaultClient.getQuitGroupManage().quitGroup(groupid,userId);
    }



    public void setGetGroupmemeberslistener(){
        Client.defaultClient.getGetGroupMembersManage()
                .setSuccessListener(getGroupMembersResponsePacket->{
                    System.out.println("群+"+getGroupMembersResponsePacket.getGroupInfo().getGroupName()+"[" + getGroupMembersResponsePacket.getGroupId() + "]中的人包括：" + getGroupMembersResponsePacket.getAllUserList());
                    for (ChannelUser channelUser : getGroupMembersResponsePacket.getAllUserList()) {
                        System.out.print(channelUser.getUserId()+" ");
                    }
                    System.out.println("\n在线成员是："+getGroupMembersResponsePacket.getOnlineUserList());
                    for (ChannelUser channelUser : getGroupMembersResponsePacket.getOnlineUserList()) {
                        System.out.print(channelUser.getUserId()+" ");
                    }
                    System.out.println("");
                })
                .setFailListener(getGroupMembersResponsePacket->{
                    System.out.println("群[" + getGroupMembersResponsePacket.getGroupId() + "]信息获取失败,原因是"+getGroupMembersResponsePacket.getReason() );
                })
                //发送到服务器成功
                .setSendSuccessListener(this::printSuccess)
                //发送到服务器失败
                .setSendFailListener(this::printFail);
    }

    public void getGroupmemebers(Long groupid){
        Client.defaultClient.getGetGroupMembersManage().getGroupMembers(groupid);
    }
    public void setGetGroupListListener(){
        Client.defaultClient.getGetGroupListResponseManage()
                .setSuccessListener((responsePacket)->{
                    System.out.println("获取到用户"+responsePacket.getUserId()+"的群列表为：");
                    for (GroupInfo groupInfo : responsePacket.getGroupInfoList()) {
                        System.out.println("群："+groupInfo.toString());
                    }
                })
                .setFailListener((responsePacket)->{
                    System.out.println("获取群列表失败,原因是"+responsePacket.getReason());
                })
                //发送到服务器成功
                .setSendSuccessListener(this::printSuccess)
                //发送到服务器失败
                .setSendFailListener(this::printFail);
    }
    public void getGroupList(Long userid){
        Client.defaultClient.getGetGroupListResponseManage().getGroupList(userid);
    }

    public void setGroupMessageListener(){
        Client.defaultClient.getGroupMessageManage()
                .setHandlerListener((messageResponsePacket)->{
                    System.out.println(messageResponsePacket.getDate()+"收到群"+messageResponsePacket.getToGroupId()
                            +" "+messageResponsePacket.getFromUserId()
                            +messageResponsePacket.getFromUserName()
                            +"发出的消息["+messageResponsePacket.getMessageId()+"]："+messageResponsePacket.getMessage());
                })
                //发送到服务器成功
                .setSendSuccessListener(this::printSuccess)
                //发送到服务器失败
                .setSendFailListener(this::printFail);
    }
    public void sendGroupMessage(Long groupId,String msg){
        Client.defaultClient.getGroupMessageManage().sendGroupMessage(groupId,msg);
    }

    public void setGroupOfflineMessageListener(){
        Client.defaultClient.getOffLineGroupMessageManage()
                .setSuccessListener((msg)->{
                    System.out.println("收到离线群消息");
                    for (GroupMsgOffline groupMsgOffline : msg.getGroupMsgOfflineList()) {
                        //TODO 循环的每一次，就是一个群有需要拉取群消息
                        //下面是使用样例
                        System.out.println(groupMsgOffline.getGroupid()+"群收到消息："+groupMsgOffline);
                        Map<Long, GroupInfo> groupInfoMap = msg.getGroupInfoMap();
                        GroupInfo groupInfo=null;
                        if(groupInfoMap!=null)
                            groupInfo= groupInfoMap.get(groupMsgOffline.getGroupid());
                        System.out.println("群的信息是："+groupInfo);
                        System.out.println("------------------------------");
                        //收到离线群消息之后，可以按照需求请求服务器推送群历史消息！
                        Client.defaultClient.getPullMessageManage()
                                .pullMessageGroupByFromMessageId(
                                        groupMsgOffline.getFirstgroupmsgid()
                                        ,groupMsgOffline.getGroupid()
                                        ,true);

                    }
                })
                .setFailListener((msg)->{
                    System.out.println("请求离线群消息失败，原因是："+msg.getReason());
                })
                //发送到服务器成功
                .setSendSuccessListener(this::printSuccess)
                //发送到服务器失败
                .setSendFailListener(this::printFail);
    }
    public void getGroupOfflineMessage(){
        Client.defaultClient.getOffLineGroupMessageManage()
                .getOffLineGroupMessage();
    }

    public void setExtraGroupMessageListener(){
        Client.defaultClient.getExtraGroupManage()
                .setHandlerListener((msg)->{
                    //对应消息的处理，其中test1需要自己映射包！
                    if(list.get(msg.getType())==test1.class){
                        test1 test1= ExtraPacketHelper.getObject(test1.class,msg);
                        System.out.println(test1);
                    }
                })
                .setSendSuccessListener(this::printSuccess)
                .setSendFailListener(this::printFail)
                ;

    }
    public void sendExtraGroupMessage(){
        test1 box=new test1();
        box.setStr("nihao");
        box.setB(true);
        box.setX(100);
        Client.defaultClient.getExtraGroupManage()
                .sendExtraGroup(2L,box,list.indexOf(test1.class));
    }
    public void setGetInfoAndFriendsListener(){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .setGetUserInfoListener(channeluser->{
                    System.out.println("获取到消息为"+channeluser+channeluser.getUserName()+"id="+channeluser.getUserId());
                })
                .setAddFriendslistener((channelUserList)->{
                    //添加好友的两个人的信息
                    System.out.println("添加好友"+channelUserList);
                    for (ChannelUser channelUser : channelUserList) {
                        System.out.println("id="+channelUser.getUserId()+"name="+channelUser.getUserName());
                    }
                })
                .setDeleteFriendslistener(channelUserList->{
                    //删除好友的两个人的信息
                    System.out.println("删除好友"+channelUserList);
                    for (ChannelUser channelUser : channelUserList) {
                        System.out.println("id="+channelUser.getUserId()+"name="+channelUser.getUserName());
                    }
                })
                .setAllFriendslistener(channelUserList->{
                    //所有好友
                    System.out.println("所有好友"+channelUserList);
                    for (ChannelUser channelUser : channelUserList) {
                        System.out.println("id="+channelUser.getUserId()+"name="+channelUser.getUserName());
                    }
                })
                .setOnLineFriendslistener(channelUserList->{
                    //所有在线好友
                    System.out.println("在线好友"+channelUserList);
                    for (ChannelUser channelUser : channelUserList) {
                        System.out.println("id="+channelUser.getUserId()+"name="+channelUser.getUserName());
                    }
                })
                ;
    }
    //获取某个用户的信息
    public void getUserInfo(Long userId){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .getUserInfo(userId);//1
    }
    //添加好友（可以别人对别人，这个更多是需要服务器确认添加的好友）
    public void addFriends(Long userId,Long userId2){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .addFriends(userId, userId2);//2
    }
    //删除好友（可以别人对别人，这个更多是需要服务器确认删除的好友）
    public void deleteFriends(Long userId,Long userId2){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .deleteFriends(userId, userId2);//3
    }
    //获取所有好友
    public void getAllFriends(Long userId){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .getAllFriends(userId);//4
    }
    //获取在线好友
    public void getOnLineFriends(Long userId){
        Client.defaultClient.getGetInfoAndFriendsManage()
                .getOnLineFriends(userId);//5
    }

}


//TEMPMANAGE
//tEMPMANAGE
//TRESPONHANDLER
//    private volatile TEMPMANAGE tEMPMANAGE=null;
//    public TEMPMANAGE getTEMPMANAGE(){
//        //尝试获取这个对象-双校验;
//        TRESPONHANDLER handler=nettyClient.channel.pipeline().get(TRESPONHANDLER.class);
//        if(nettyClient.channelisOK&&nettyClient.channel!=null) {
//            if(handler==null)
//                handler=nettyClient.channel.pipeline().get(TRESPONHANDLER.class);
//            if(handler==null)
//                    System.err.println("请检查NettyClient是否添加该handler");
//            //双重锁检测
//            if (tEMPMANAGE == null&&handler!=null) {
//                synchronized (this) {
//                    if (tEMPMANAGE == null&&handler!=null) {
//                        tEMPMANAGE = new TEMPMANAGE(nettyClient.channel,handler);
//                    }
//                }
//            }
//        }
//        return tEMPMANAGE;
//    }