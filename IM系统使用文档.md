



# IM系统使用手册





## 客户端

### 1. 初始化

```java
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
> 调用此方法后，离线消息就会被清除，如果出现意外状况仍需要获取信息，可以通过获取消息来得到

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
    	//传递好参数即可：接收方id，自定义消息体，消息类型type（int），消息类型（Class）
        .sendExtraMessage(toId,MsgBox,type,Class);
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
        //传递好参数即可：接收方id，自定义消息体，消息类型type（int），消息类型（Class）
        .sendExtraMessage(toid,box,list.indexOf(test1.class),test1.class);
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





### 群组操作