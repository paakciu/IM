# IM

---基于netty高性能底层通信

[个人博客](http://paakciu.top) (已经过期)

## 简介

#### 基于java实现的IM即时通信模块，尽量避免使用框架工具，以便移植（主要是希望安卓端也能用，或者其他能运行java虚拟机的嵌入式设备）

#### 这是毕业设计的部分代码，已申请软著权，已经不再维护，写得比较一般，但是对系统的增减都比较方便，可以作一定的参考。

#### 根据这一次的项目经验，希望重做一个。已新建仓库：pitim



## To Do List

### 正在完成：

- [x] 

### 未完成:

- [ ] 参照环信云，腾讯云，网易云的API

  

#### 管理部分------

- [ ] 学习springBoot

#### 安卓部分-----

- [x] 页面规划
- [x] 交互设计

### 已完成：
- [x] 实现客户端登录

- [x] 自由协议

- [x] 协议编码解码器

- [x] 客户端与服务端收发消息

- [x] pipeline 和 channelHandler

- [x] 解决拆包粘包的问题

- [x] 热插拔和客户端身份校验

- [x] 客户端互聊

- [x] 性能优化

- [x] 消息收发

- [x] 心跳与空闲检测

- [x] 群聊的发起和通知

- [x] 群聊消息的收发

- [x] 群成员管理

- [x] 透传消息（控制消息），这个可以使用Extra消息拓展，但是目前没必要

- [x] 离线消息的拉取

- [x] 拓展消息类ExtraMessage

  







优化方向记录：

1. 关注每个new 如果可以单例的话，没有必要每个连接都要new一个对象，使用单例模式

- *注意：千万记得要加 `@ChannelHandler.Sharable` 注解*

2. 有一些参数写死了的，static final的，探讨是否存在延迟产生的可能

3. 调用 `ctx.xxx()` 可以直接跳到编译码处理器， 替换`ctx.channel().xxx()` 







## 客户端使用手册
