package top.paakciu;

import java.util.concurrent.*;

/**
 * @author paakciu
 * @ClassName: test05
 * @date: 2021/3/23 23:03
 */
public class test05 {
    private static final String SUCCESS = "success";

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("------------------任务开始执行---------------------");

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                System.out.println("submit方法执行任务完成" + "   thread name: " + Thread.currentThread().getName());
                return SUCCESS;
            }
        });

        try {
            String s = future.get();
            if (SUCCESS.equals(s)) {
                String name = Thread.currentThread().getName();
                System.out.println("经过返回值比较，submit方法执行任务成功    thread name: " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------main thread end---------------------");

        
    }

}
