package com.wrh.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:24 2019/1/21 0021
 * @Modified By:
 */
public class TestInterrupted {
    /*public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println(Thread.interrupted());
            }
        });

        t1.setDaemon(true);
        t1.start();

        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
    }*/

    public static void main(String[] args) {
        System.out.println("main thread is interrupted?" + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("main thread is interrupted === 2? " + Thread.currentThread().isInterrupted());

        try {
            System.out.println("enter this method!");
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("i will be interrupted still.");
        }
    }
}
