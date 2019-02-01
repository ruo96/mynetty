package com.wrh.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:26 2019/1/25 0025
 * @Modified By:
 */
public class TestLock {

    public synchronized void method1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " method 1 enter! ");

        TimeUnit.SECONDS.sleep(3);
    }
    public synchronized void method2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " method 2 enter! ");

        TimeUnit.SECONDS.sleep(3);
    }

    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        new Thread(() -> {
            try {
                testLock.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
        new Thread(() -> {
            try {
                testLock.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }

}
