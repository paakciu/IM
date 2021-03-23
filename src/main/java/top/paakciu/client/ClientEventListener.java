package top.paakciu.client;

import io.netty.channel.Channel;

public interface ClientEventListener {

    default void onInitChannel(){
        System.out.println("IO处理逻辑初始化");
    }
    /**
     * 一定要使用额外的线程来处理数据！
     */
    default void onConnectSuccess(Channel channel){
        System.out.println("连接成功！");
    }
    /**
     * 一定要使用额外的线程来处理数据！
     */
    default void onConnectFail(int retry){
        System.out.println("连接失败");
    }
}
