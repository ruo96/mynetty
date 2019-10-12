package com.wrh.thread.ABCprint;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:45 2019/10/12 0012
 * @Modified By:
 */

/**
 * 值得注意的是ReentrantLock是可重入锁，它持有一个锁计数器，当已持有锁的线程再次获得该锁时计数器值加1，
 * 每调用一次lock.unlock()时所计数器值减一，直到所计数器值为0，此时线程释放锁。示例如下：
 */
public class TestABCbyReentryLock {
    private ReentrantLock lock = new ReentrantLock();

    public void testReentrantLock() {
        // 线程获得锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lock");
            long beginTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - beginTime < 100) {
            }
            //线程再次获得该锁（可重入）
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock again");
                long beginTime2 = System.currentTimeMillis();
                while (System.currentTimeMillis() - beginTime2 < 100) {
                }
            } finally {
                // 线程第一次释放锁
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release lock");
            }
        } finally {
            // 线程再次释放锁
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock again");
        }
    }

    public static void main(String[] args) {
        final TestABCbyReentryLock test = new TestABCbyReentryLock();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                test.testReentrantLock();
            }
        }, "A");
        thread.start();
    }



}
