package cn.paakciu.client;

import cn.paakciu.config.IMconfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;


public class netty_client {
    public static void main(String[] args)
    {

        NioEventLoopGroup workerGroup =new NioEventLoopGroup();

        Bootstrap bootstrap =new Bootstrap();
        bootstrap
                //指定线程模型
                .group(workerGroup)
                //指定IO类型为NIO
                .channel(NioSocketChannel.class)
                //指定IO的处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });

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

        connect(bootstrap,IMconfig.host,IMconfig.port);

    }
    //建立连接
    public static void connect (Bootstrap bootstrap,String host,int port){
        bootstrap
                .connect(host, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
                    } else {
                        System.out.println("连接失败");
                        //这里应该要有个随机退避算法
                        connect(bootstrap,IMconfig.host,IMconfig.port);
                    }
                });
    }


}
