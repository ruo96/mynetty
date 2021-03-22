package com.wrh.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wuruohong
 * @Classname TestParkUnPark
 * @Description TODO
 * @Date 2021/3/18 11:12
 */
public class TestParkUnPark {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread start: " + Thread.currentThread().getName());
            LockSupport.park(); // 阻塞自己
            System.out.println("Thread end: " + Thread.currentThread().getName());
        });

        thread.setName("A");
        thread.start();

        System.out.println("Main thread sleep 3 second: " + Thread.currentThread().getId());
        TimeUnit.SECONDS.sleep(3);
        LockSupport.unpark(thread); // 唤醒线程 A
    }
}
