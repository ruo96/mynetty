package com.wrh.thread.interrupt;

import java.util.concurrent.TimeUnit;

public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA(1);
        ThreadA threadB = new ThreadA(5);
        Thread thread1 = new Thread(threadA);
        Thread thread2 = new Thread(threadB);
        thread1.start();
        TimeUnit.SECONDS.sleep(3);
        thread2.start();
    }
}
