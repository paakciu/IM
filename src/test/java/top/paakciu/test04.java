package top.paakciu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * @author paakciu
 * @ClassName: test04
 * @date: 2021/3/23 20:29
 */
public class test04 {
    public static void main(String[] args) {
        // 两个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        System.out.println("new : " + Thread.currentThread().getName());
        //jdk1.8之前的实现方式
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task started!");
                System.out.println("task started! : " + Thread.currentThread().getName());
//                try {
//                    //模拟耗时操作
                    for(int i=1;i<1000;i++);
                    System.out.println("nihao");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return "task finished!";
            }
        }, executor);

        //采用lambada的实现方式
        future.thenAccept(e -> {
            System.out.println(e + " ok");
            System.out.println("ok : " + Thread.currentThread().getName());
        });

        System.out.println("main thread is running");
    }
}
