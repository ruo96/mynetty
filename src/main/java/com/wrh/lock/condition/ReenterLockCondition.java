package com.wrh.lock.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuruohong
 * @Classname ReenterLockCondition
 * @Description TODO
 * @Date 2021/4/30 15:23
 */
@Slf4j
public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static Condition condition1 = lock.newCondition();

    @Override
    public void run() {
        lock.lock();
        try {
            condition.await();
            log.info("条件满足，线程 %s 运行！", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.info("");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition reenterLockCondition = new ReenterLockCondition();
        Thread t1 = new Thread(reenterLockCondition);
        t1.setName("T1");
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        log.info("t1 满足条件，开始通知");
        lock.lock();
        try {
            condition1.signal();
        } catch (Exception e) {
            log.info("  main exception");
        }finally {
            log.info("unlock");
            lock.unlock();
        }
        log.info("end");
    }
}
