package com.wrh.thread.hook;

import java.util.concurrent.TimeUnit;

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
}
