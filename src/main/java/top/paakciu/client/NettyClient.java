package top.paakciu.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.Attribute;
import top.paakciu.client.handler.LoginResponseHandler;
import top.paakciu.client.handler.MessageResponseHandler;
import top.paakciu.config.IMConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import top.paakciu.protocal.codec.handler.PacketDecoder;
import top.paakciu.protocal.codec.handler.PacketEncoder;
import top.paakciu.protocal.codec.handler.PreFrameDecoder;
import top.paakciu.protocal.packet.MessagePacket;
import top.paakciu.utils.AttributesHelper;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args)
    {
        //线程组
        NioEventLoopGroup workerGroup =new NioEventLoopGroup();
        //引导类
        Bootstrap bootstrap =new Bootstrap();
        //核心配置
        bootstrap
                //指定线程模型
                .group(workerGroup)
                //指定IO类型为NIO
                .channel(NioSocketChannel.class)
                //指定IO的处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    //连接初始化
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        System.out.println("IO处理逻辑初始化");
                        //这里是责任链模式，然后加入逻辑处理器
                        socketChannel.pipeline()
                                //.addLast(new clientHandler())
                                .addLast(new PreFrameDecoder())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new PacketEncoder());
//                                .addLast(new ZhanBaoClientHandler());
                    }
                });
        BootstrapExtraConfig(bootstrap);


        //启动连接
        connect(bootstrap, IMConfig.HOST, IMConfig.PORT,MAX_RETRY);

    }
    public static Bootstrap BootstrapExtraConfig(Bootstrap bootstrap){
        //额外的配置
        bootstrap
                // 设置TCP底层属性
                //连接的超时时间
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                //是否开启TCP底层心跳机制
                .option(ChannelOption.SO_KEEPALIVE, true)
                //是否开启Nagle，即高实时性（true）减少发送次数（false）
                .option(ChannelOption.TCP_NODELAY, true);
        return bootstrap;
    }


    //建立连接
    public static void connect (Bootstrap bootstrap,String host,int port,int retry)
    {
        bootstrap
                .connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");

                        Channel channel = ((ChannelFuture) future).channel();
                        // 连接成功之后，启动控制台线程
                        startConsoleThread(channel);
                    }
                    else {
                        //这里应该要有个随机退避算法
                        if (retry == 0) {
                            System.err.println("重试次数已用完，放弃连接！");
                            return;
                        }
                        // 第几次重连
                        int order = (MAX_RETRY - retry) + 1;
                        // 此次重连的间隔时间
                        int delay = 1 << order;
                        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                        //使用计划任务来实现退避重连算法
                        bootstrap.config().group().schedule(
                                () -> connect(bootstrap, host, port, retry - 1)
                                ,delay
                                ,TimeUnit.SECONDS
                        );
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {

                //Attribute<Boolean> loginAttr = channel.attr(AttributeKey.exists("login")?AttributeKey.valueOf("login"):AttributeKey.newInstance("login"));

                if (AttributesHelper.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessagePacket packet = new MessagePacket();
                    packet.setMessage(line);
                    //ByteBuf byteBuf = PacketCodec.encode(channel.alloc().buffer(), packet);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }


}
