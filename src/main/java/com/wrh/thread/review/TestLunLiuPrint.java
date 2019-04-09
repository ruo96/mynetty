package com.wrh.thread.review;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:02 2019/3/12 0012
 * @Modified By:
 */
@Slf4j
public class TestLunLiuPrint {

    private static Object lockA = new Object();
    private static Object lockB = new Object();
    private static Object lockC = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true){
                log.info("线程1 等待 C 锁");
                synchronized (lockC){
                    log.info("线程1 拿到 C 锁");
                    synchronized (lockA){
                        log.info("线程1 拿到 A 锁");
                        log.info("{} 输出 1",Thread.currentThread().getName());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("线程1 唤醒 A 锁");
                        lockA.notifyAll();
                    }
                    try {
                        log.info("线程1 放弃 C 锁");
                        lockC.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"线程1");

//        第一个线程占有C锁, 通知A锁,然后在C锁上面等待, 第二个占有A锁, 然后在A锁上面等待, 第三个占有B锁, 然后在B锁上面等待

        Thread t2 = new Thread(() -> {
            while(true){
                log.info("线程2 等待 A 锁");
                synchronized (lockA){
                    log.info("线程2 拿到 A 锁");
                    synchronized (lockB){
                        log.info("线程2 拿到 B 锁");
                        log.info("{} 输出 2",Thread.currentThread().getName());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("线程2 唤醒 B 锁");
                        lockB.notifyAll();
                    }
                    try {
                        log.info("线程2 放弃 A 锁");
                        lockA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"线程2");

        Thread t3 = new Thread(() -> {
            while(true){
                log.info("线程3 等待 B 锁");
                synchronized (lockB){
                    log.info("线程3 拿到 B 锁");
                    synchronized (lockC){
                        log.info("线程3 拿到 C 锁");
                        log.info("{} 输出 3",Thread.currentThread().getName());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("线程3 唤醒 C 锁");
                        lockC.notifyAll();
                    }
                    try {
                        log.info("线程3 放弃 B 锁");
                        lockB.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"线程3");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t3.start();
    }



}
