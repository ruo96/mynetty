package com.wrh.thread.hook;

import akka.remote.artery.aeron.TaskRunner;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:20 2019/2/1 0001
 * @Modified By:
 */
public class TestHook {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+" occur exception");
            e.printStackTrace();
        });

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {

            }
            System.out.println(1/0);
        },"test-thread");
        thread.start();
    }


    @Test
    public void Test33() {
        //1.实现一个自己的线程池工厂
        ThreadFactory factory = (Runnable r) -> {
            //创建一个线程
            Thread t = new Thread(r);
            //给创建的线程设置UncaughtExceptionHandler对象 里面实现异常的默认逻辑
            Thread.setDefaultUncaughtExceptionHandler((Thread thread1, Throwable e) -> {
                // 在此设置统计监控逻辑
                System.out.println("线程工厂设置的exceptionHandler" + e.getMessage());
            });
            return t;
        };

// 2.创建一个自己定义的线程池，使用自己定义的线程工厂
        ExecutorService service = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue(10),factory);

//3.提交任务
        service.submit(()->{
            int i=1/0;
        });
    }
}
