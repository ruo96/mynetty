package com.wrh.aqs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuruohong
 * @Classname TestAqs
 * @Description TODO
 * @Date 2020/10/23 18:40
 */
@Slf4j
public class TestAqs {
    @Test
    public void Test14() {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;

    }

    @Test
    public void Test23() throws InterruptedException {
        Lock lock = new ReentrantLock();
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("t1 start");
                lock.lock();
                System.out.println("t1 lock");

                try {
                    System.out.println("t1 await");
                    condition1.await();
                    System.out.println("t1 unawait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println("t1 run again");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start");
                lock.lock();
                System.out.println("t2 lock");
                try {
                    System.out.println("t2 await");
                    condition2.await();
                    System.out.println("t2 unawait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println("t2 run again");
            }
        });

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(" main call signal ");
        lock.lock();
        condition1.signal();
        lock.unlock();
        TimeUnit.SECONDS.sleep(5);



    }
}
