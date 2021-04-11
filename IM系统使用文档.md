



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



### 4. 发送普通文本消息

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



### 5. 离线消息的获取

> 直接调用这个方法，就会把离线消息推送到“4.普通文本消息”处，将作为普通消息处理
>
> 调用此方法后，离线消息就会被清除，如果出现意外状况仍需要获取信息，可以通过获取消息来得到

```java
Client.defaultClient.getOffLineMessage();
```







> 注：由于链式调用的函数可有可无，以下
>
> ```java
> //发送到服务器成功
> .setSendSuccessListener(()->{
>     System.out.println("发送到服务器成功");
> })
> //发送到服务器失败
> .setSendFailListener(()->{
>     System.out.println("发送到服务器失败");
> });
> ```
> 
> 这种发送到服务器成功与失败的监听就省略不写在文档上了

### 群组操作