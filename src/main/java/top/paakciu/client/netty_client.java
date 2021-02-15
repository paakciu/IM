package top.paakciu.client;

import top.paakciu.config.IMConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class netty_client {
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
                        //这里是责任链模式，然后加入一个逻辑处理器
                        socketChannel.pipeline().addLast(new clientHandler());
                    }
                });

        //额外的配置
        bootstrap
                // 绑定自定义属性到 channel
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                // 设置TCP底层属性
                //连接的超时时间
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                //是否开启TCP底层心跳机制
                .option(ChannelOption.SO_KEEPALIVE, true)
                //是否开启Nagle，即高实时性（true）减少发送次数（false）
                .option(ChannelOption.TCP_NODELAY, true);

        //启动连接
        connect(bootstrap, IMConfig.HOST, IMConfig.PORT,MAX_RETRY);

    }
    //建立连接
    public static void connect (Bootstrap bootstrap,String host,int port,int retry)
    {
        bootstrap
                .connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
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
                        bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1)
                                                            , delay
                                                            , TimeUnit.SECONDS);
                                                            //上面这句的第一个参数的等效语句是
                                                            /*
                                                                new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        connect(bootstrap, host, port, retry - 1);
                                                                    }
                                                                }
                                                            */

                    }
                });


    }


}
