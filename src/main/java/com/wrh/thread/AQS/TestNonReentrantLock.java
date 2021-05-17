package com.wrh.thread.AQS;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuruohong
 * @Classname TestNonReentrantLock
 * @Description TODO
 * @Date 2021/4/28 18:00
 */
@Slf4j
public class TestNonReentrantLock {
    private static int j = 0;
    public static void main(String[] args) throws InterruptedException {
        NonReentrantLock  nonReentrantLock = new NonReentrantLock();

        Runnable runnable = () -> {
            //获取锁
            nonReentrantLock.lock();
            for (int i = 0; i < 100000; i++) {
                j++;
            }
            //释放锁
            nonReentrantLock.unlock();
        };

        Thread thread = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);

        thread.start();
        threadTwo.start();

        thread.join();
        threadTwo.join();

        log.info(">>> j is： {}",j);
    }
}
