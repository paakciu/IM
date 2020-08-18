package cn.paakciu.server;

import cn.paakciu.core.NIOserver;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.function.BooleanSupplier;

public class netty_server {
    public static void main(String[] args)
    {
        //监听欢迎端口-线程组
        NioEventLoopGroup bossGroup =new NioEventLoopGroup();
        //处理每条链接数据读写的线程组
        NioEventLoopGroup workerGroup =new NioEventLoopGroup();

        //服务器启动-引导类
        ServerBootstrap serverBootstrap =new ServerBootstrap();

        //核心配置部分
        serverBootstrap
                //配置两大线程组
                .group(bossGroup,workerGroup)
                //指定IO模型 如果要设定成BIO的模型，则改成OioServerSocketChannel.class，NIO则是NioServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //定义后续每条连接的数据读写，业务处理逻辑，这里的NioSocketChannel是netty对NIO类型连接的抽象，如Socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println(nioSocketChannel.attr(AttributeKey.valueOf("clientKey")).get());
                    }
                });



        //handler方法，指定服务器启动过程中的一些逻辑，一般用不到，NioServerSocketChannel是Nio的服务器连接的抽象，如serverSocket
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        System.out.println("服务器启动中");
                    }
                });

        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                //给Server连接维护一个map
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                //给每条连接连接维护map
                .childAttr(clientKey, "clientValue")
                //存放已经三次握手的请求的队列的最大长度
                .option(ChannelOption.SO_BACKLOG, 1024)
                //开启TCP底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //实时性设置，开则有数据发送马上发送，关泽减少发送次数
                .childOption(ChannelOption.TCP_NODELAY, true)
                ;


        //绑定端口
        serverBootstrap.bind(4396);

    }

    public static void bind(ServerBootstrap serverBootstrap,int port)
    {
        //Lambda写法
        serverBootstrap.bind(port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("端口["+port+"]监听成功！");
            }
            else{
                System.out.println("端口["+port+"]监听失败！");
                //重新连接，端口自动增一重试
                bind(serverBootstrap, port+1);
            }
        });

        //上面的 等效格式
//        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if(future.isSuccess()){
//                    System.out.println("端口["+port+"]监听成功！");
//                }
//                else{
//                    System.out.println("端口["+port+"]监听失败！");
//                    //重新连接，端口自动增一重试
//                    bind(serverBootstrap, port+1);
//                }
//            }
//        });

    }
}
