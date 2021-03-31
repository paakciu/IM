package top.paakciu.client.listener;

import io.netty.channel.Channel;

public interface ClientEventListener {
    /**
     * 已经是异步的方法了，使用的时候会另开一个线程去完成
     */
    default void onInitChannel(){
        System.out.println("IO处理逻辑初始化");
    }
    /**
     * 已经是异步的方法了，使用的时候会另开一个线程去完成
     */
    default void onConnectSuccess(Channel channel){
        System.out.println("连接成功！");
    }
    /**
     * 已经是异步的方法了，使用的时候会另开一个线程去完成
     */
    default void onConnectFail(int retry){
        System.out.println("连接失败");
    }
}
