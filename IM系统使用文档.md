



# IM系统使用手册





## 客户端

### 1. 初始化

```java
/**
* 初始化的方法lisntener里面的方法已经是异步方法了，不需要另外创建线程
* 线程池参数可调节IMconfig文件中的CLIENT_THREAD_POOL_NUM参数
* {@link top.paakciu.config.IMConfig}
*/
Client.defaultClient.initClienConnection(IP,port).setEventListener(new ClientEventListener() {
    @Override
    public void onInitChannel() {
        //TODO 初始化连接的时候要处理的事情

    }

    @Override
    public void onConnectSuccess(Channel channel) {
        //TODO 连接成功后要做的事情

    }

    @Override
    public void onConnectFail(int retry) {
        //TODO 连接失败后要做的事情

    }
});
```





### 2. 注册

```java
/**
* 要注意连接成功之后，才能调用该方法进行注册
* 关于注册事件的回调会在Netty的异步线程中进行，不需要建立线程。
* 链式调用！
*/
Client.defaultClient.register("username","password")
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
```



### 3. 登录

```java
Client.defaultClient.login("username","password")
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
    })
    ;

```



### 4. 单聊发送普通文本消息（NormalMessageManage）

#### 第一步，尽量先于发送 setListener

样例：

> 说明：自己发送的消息，经过服务器持久化之后，会发送到 发送方和接收方，发送方可以确认自己发送的消息的id，和发送是否持久化成功。

```java
    public void setSendListener(){
        Client.defaultClient.getNormalMessageManage()
                //收到消息的处理器
                .setHandlerListener(messageResponsePacket->{
                    //TODO 消息处理
//                    ChannelUser thisChannelUser=Client.defaultClient.getChannelUser();
//                    if(thisChannelUser!=null)
//                        if(thisChannelUser.getUserId()==messageResponsePacket.getFromUserId()){
//                            //TODO 这里的处理是收到了自己发送的消息的处理，证明消息发送正确
//                            //建议是本地发送不要马上显示，而是收到服务器返回的自己发送的这条消息后，再进行显示，这个过程一般不会很慢
//                            //这样做的好处在于，后面离线消息的推送可以复用该消息处理通道
//                            System.out.println("收到自己发送的消息！");
//                        }
//
//                    //TODO 这里写怎么处理这些数据的，下面是处理样例
//                    System.out.println(messageResponsePacket.getDate()
//                            + ": 收到服务端的消息 ID:"
//                            + messageResponsePacket.getMessageId()
//                            + "消息："
//                            + messageResponsePacket.getMessage());
//                    Long fromUserId = messageResponsePacket.getFromUserId();
//                    String fromUserName = messageResponsePacket.getFromUserName();
//                    System.out.println(fromUserId + ":"
//                            + fromUserName + " -> "
//                            + messageResponsePacket .getMessage());
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
```

#### 第二步，发送消息

```java
public void send(Long toid,String msg){
    //todo 注意不能发送给自己，如果发送给自己会返回发送到服务器失败SendFailListener
    Client.defaultClient.getNormalMessageManage().send(toid,msg);
}
```



### 5. 单聊离线消息的获取（GetOffLineMessageManage）

> 直接调用这个方法，就会把离线消息推送到“4.普通文本消息”处，将作为普通消息处理
>
> 调用此方法后，离线消息就会被清除
>
> 如果出现意外状况仍需要获取信息，可以通过获取历史消息来得到！

```java
Client.defaultClient.getGetOffLineMessageManage()
        //发送拉取离线消息请求
        .getOffLineMessage()
        //按需设置回调函数
        .setSendFailListener(this::printFail)
        .setSendSuccessListener(this::printSuccess)
        ;
```



### 6. 单聊历史消息的获取（PullMessageManage）

> 此请求会直接调用普通消息的回调--普通消息的回调会包括自己发送的东西，所以不用写回调监听

```java
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
//------------------------------------------------------------------------------------
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
//------------------------------------------------------------------------------------
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

```



### 7. 拓展消息体（ExtraManage）

- （可作为控制消息体）

> 该类型消息不会进行持久化，故无法获取离线消息，历史消息，如果有需求请开发者自行实现。
>
> 该拓展消息经过服务器后不会返回发送方（单聊普通文本消息会），原因是拓展消息体有可能负载大容量消息。
>
> 该类型消息需要拓展映射！

#### 关键代码：

```java
Client.defaultClient.getExtraManage()
        //消息处理--监听
        .setHandlerListener((msg)->{})
    	.setSendSuccessListener(this::printSuccess)
        .setSendFailListener(this::printFail)

    	//消息发送
    	//传递好参数即可：接收方id，自定义消息体，消息类型type（int）
        .sendExtraMessage(25L,box,list.indexOf(test1.class));
```

#### msg的数据结构包含：

> 拓展包的发送和获取下面有使用样例可以参考
>
> fromUserId、fromUserName、toUserId、date字段可以使用
>
> type、len、message字段已经有封装好的函数使用，例：sendExtraMessage

```java
private Long fromUserId;
private String fromUserName;
private Long toUserId;
//扩展包的类型---需要自行映射
private Integer type;
//扩展包的字节流长度，使用int，带符号数上限只能2GB
private Integer len;
//扩展包的字节流内容
private byte[] message;
//时间
private Date date;
```

#### 使用样例：

##### 第一步、建立映射表，映射关系

> 建立类型映射 int type----Class这样的映射即可，这是一个使用范例

```java
//映射表
List<Class> list= new CopyOnWriteArrayList<Class>();

public void ExtraListAdd(){
    if(!list.contains(test1.class))
        list.add(test1.class);
}

//调用该方法加入映射表
ExtraListAdd();
```

自定义消息体test1：
```java
public class test1 {
    //自定义属性
    String str;
    boolean b;
    int x;
    //TODO 自定义的消息体，一定要注意GETTER跟SETTER的完备
    //下面的getter跟setter一定要对应所有属性，这是因为本IM通信协议默认使用阿里的fastJSON进行序列化
    //如果getter跟setter缺失的话会导致数据传输字段的丢失！此坑浪费了我一下午
}
```

##### 第二步、设置消息回调Listener

```java
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
```

##### 第三步、发送消息

```java
System.out.println("请输入toid msg：");
Long toid = sc.nextLong();
String msg = sc.next();
test1 box=new test1();
box.setStr(msg);
box.setB(true);
box.setX(100);

Client.defaultClient.getExtraManage()
        //传递好参数即可：接收方id，自定义消息体，消息类型type（int）
        .sendExtraMessage(25L,box,list.indexOf(test1.class));
```



### 8. 登出

> 因为本系统使用的全部都是异步方法，登出会直接执行，关闭通道
>
> 故要把握好关闭的时机，可以在需要等待回调listener的地方使用。
>
> 此方法没有监听listener方法

```java
Client.defaultClient.logout();
```





> 注：由于链式调用的函数可有可无，以下
>
> ```java
> //发送到服务器成功
> .setSendSuccessListener(()->{
>  System.out.println("发送到服务器成功");
> })
> //发送到服务器失败
> .setSendFailListener(()->{
>  System.out.println("发送到服务器失败");
> });
> 
> 或者
>  
> .setSendFailListener(this::printFail)
> .setSendSuccessListener(this::printSuccess)
> ```
>
> 这种发送到服务器成功与失败的监听就不固定地写在文档上了



### 9.错误监听

> 其中，getErrorCode是对应着错误包的byte号
>
> reason是错误的原因

```java
Client.defaultClient.setErrorListener((errorMessagePacket)->{
    System.out.println("收到出问题的消息"+errorMessagePacket.getReason()+errorMessagePacket.getErrorCode());
        });
```

##### Errorcode对应表：

> 客户端收到的错误只有xxx_response系列的错误。

```java
//有可能未来得及更新

```





### 10. 好友操作组(GetInfoAndFriendsManage)

#### 监听:

```java
Client.defaultClient.getGetInfoAndFriendsManage()
    .setGetUserInfoListener(channeluser->{
        System.out.println("获取到消息为"+channeluser);
    })
    .setAddFriendslistener((channelUserList)->{
        //添加好友的两个人的信息
        System.out.println("添加好友"+channelUserList);
    })
    .setDeleteFriendslistener(channelUserList->{
        //删除好友的两个人的信息
        System.out.println("删除好友"+channelUserList);
    })
    .setAllFriendslistener(channelUserList->{
        //所有好友
        System.out.println("所有好友"+channelUserList);
    })
    .setOnLineFriendslistener(channelUserList->{
        //所有在线好友
        System.out.println("在线好友"+channelUserList);
    })
    ;
```

#### 1.获取消息

```java
//根据id 获取相关信息（只有username）
public GetInfoAndFriendsManage getUserInfo(Long userId);
//根据id添加好友，id先后顺序不重要
public GetInfoAndFriendsManage addFriends(Long userId,Long userId2);
//根据id删除好友，id先后顺序不重要
public GetInfoAndFriendsManage deleteFriends(Long userId,Long userId2);
//获取好友列表
public GetInfoAndFriendsManage getAllFriends(Long userId);
//获取在线好友列表
public GetInfoAndFriendsManage getOnLineFriends(Long userId);
```

```java
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
```









### 群组操作

### 1.建群(CreateGroupManage)

```java

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
//-------------------------------------------------------
		//建群请求
		.createGroup(userIdList,groupName);
```



### 2. 加入群(getJoinGroupManage)

> 添加监听使用样例：

```java
Client.defaultClient.getJoinGroupManage()
        .setSuccessListener((joinGroupResponsePacket)->{
            if (joinGroupResponsePacket.isNewJoin()
                &&joinGroupResponsePacket.isOnLine()) {
                System.out.println(joinGroupResponsePacket.getUserName()
                                   +"[在线]新加入群[" 
                                   + joinGroupResponsePacket.getGroupId() 
                                   + "]成功!");
            }
            else if (joinGroupResponsePacket.isNewJoin()
                     &&!joinGroupResponsePacket.isOnLine()) {
                System.out.println(joinGroupResponsePacket.getUserName()
                                   +"[离线]新加入群[" 
                                   + joinGroupResponsePacket.getGroupId() 
                                   + "]成功!");
            }
            else if(!joinGroupResponsePacket.isNewJoin()
                    && joinGroupResponsePacket.isOnLine()){
                System.out.println(joinGroupResponsePacket.getUserName()
                                   +"登录群[" 
                                   + joinGroupResponsePacket.getGroupId() 
                                   + "]成功!");
            }
        })
        .setFailListener(JoinGroupResponsePacket->{
            System.err.println("加入群[" 
                               + JoinGroupResponsePacket.getGroupId() 
                               + "]失败，原因为：" 
                               + JoinGroupResponsePacket.getReason());
        });
```

> 加群请求：

```java
Client.defaultClient.getJoinGroupManage().joinGroup(groupId,userId);
```



### 3. 退群(QuitGroupManage)

监听：

```java
Client.defaultClient.getQuitGroupManage()
        //监听
        .setSuccessListener((responsePacket)->{
            System.out.println(responsePacket.getUserId()+"退出群聊[" + responsePacket.getGroupId() + "]成功！");
        })
        .setFailListener((responsePacket)->{
            System.out.println(responsePacket.getUserId()+"退出群聊[" + responsePacket.getGroupId() + "]失败！");
        });
```

调用：

```java
public void quitGroup(Long groupid,Long userId){
	Client.defaultClient.getQuitGroupManage().quitGroup(groupid,userId);
}
```





### 4. 获取群信息(GetGroupMembersManage)

> 获取的信息包括
>
> 群信息（id、groupinfo）
>
> 群全部成员列表
>
> 群在线成员列表
>
> --失败后
>
> 失败原因

监听回调：

```java
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
```

调用

```java
public void getGroupmemebers(Long groupid){
    Client.defaultClient.getGetGroupMembersManage().getGroupMembers(groupid);
}
```



### 5. 群消息(GroupMessageManage)



```java
Client.defaultClient.getGroupMessageManage()
        .setHandlerListener((messageResponsePacket)->{
            System.out.println(messageResponsePacket.getDate()+"收到群"+messageResponsePacket.getToGroupId()
                    +" "+messageResponsePacket.getFromUserId()
                    +messageResponsePacket.getFromUserName()
                    +"发出的消息："+messageResponsePacket.getMessage());
        })
        //发送到服务器成功
        .setSendSuccessListener(this::printSuccess)
        //发送到服务器失败
        .setSendFailListener(this::printFail);
```

发送：

```java
public void sendGroupMessage(Long groupId,String msg){
    Client.defaultClient.getGroupMessageManage().sendGroupMessage(groupId,msg);
}
```



### 6. 群组历史消息的获取（PullMessageManage）

> 此请求跟单聊请求共享同一个manage
>
> 此请求会直接调用普通群组消息的回调，所以不用写回调监听
>
> 使用样例：

```java
/**
 * 方式1
 * 参数说明：
 * 推荐使用：这个可以用于群组离线消息的拉取，因为群组的离线消息的处理跟普通消息不一样
 * 这是因为群组消息有可能非常庞大，按需获取即可！
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
```



### 7. 获取哪个群有离线消息（OffLineGroupMessageManage）

**注意！并非是直接获取离线消息，而是获得哪个群id有离线消息，以及对应的最开始的离线消息id.**

> 群组的离线消息的处理跟普通消息不一样,这是因为群组消息有可能非常庞大，按需获取即可！

```java
Client.defaultClient.getOffLineGroupMessageManage()
        .setSuccessListener((msg)->{
            System.out.println("收到离线群消息");
            for (GroupMsgOffline groupMsgOffline : msg.getGroupMsgOfflineList()) {
                //TODO 循环的每一次，就是一个群有需要拉取群消息
                //下面是使用样例
                System.out.println(groupMsgOffline.getGroupid()
                                   +"群收到消息："+groupMsgOffline);
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
```

调用请求：

> 直接调用这个方法，就会把离线消息推送到“5.群消息”处，将作为普通群消息处理
>
> 调用此方法后，离线消息就会被清除
>
> 如果出现意外状况仍需要获取信息，可以通过获取历史消息来得到！

```java
public void getGroupOfflineMessage(){
    Client.defaultClient.getOffLineGroupMessageManage()
        .getOffLineGroupMessage();
}
```





### 8. 群拓展消息体

> 具体可以参照单聊”7.拓展消息体“，原理一样
>
> 而且只要int type<--->Class clazz的映射关系能对应上
>
> 单聊拓展消息体跟群拓展消息体可以独立使用2套映射系统
>
> 这里演示只使用共享的映射



回调监听：

```java
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
```

调用：

```java
public void sendExtraGroupMessage(){
    test1 box=new test1();
    box.setStr("nihao");
    box.setB(true);
    box.setX(100);
    Client.defaultClient.getExtraGroupManage()
        .sendExtraGroup(2L,box,list.indexOf(test1.class));
}
```







### 9. 群列表

```java
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
```

```java
Client.defaultClient.getGetGroupListResponseManage().getGroupList(userid);
```

