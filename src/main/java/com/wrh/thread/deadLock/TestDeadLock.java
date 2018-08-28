package com.wrh.thread.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:00 2018/8/28 0028
 * @Modified By:
 */
@Slf4j
public class TestDeadLock {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run(){
                synchronized (lock1){
                    log.info("thread 1 get lock1 ");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        log.info("thread 1 get lock2");
                    }
                }
                log.info("thread 1 end!");
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                synchronized (lock2){
                    log.info("thread 2 get lock2 ");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        log.info("thread 2 get lock1");
                    }
                }
                log.info("thread 2 end!");
            }
        }.start();
    }

}
