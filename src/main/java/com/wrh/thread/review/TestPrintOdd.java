package com.wrh.thread.review;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:04 2019/3/12 0012
 * @Modified By:
 */
@Slf4j
public class TestPrintOdd {

    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(count <100){
                synchronized (lock){

                    log.info("打印偶数:　{}",count);
                    count ++;
                    lock.notifyAll();
                    try {
                        if(count <100){

                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while(count < 100){

                synchronized (lock){

                    log.info("打印奇数:　{}",count);
                    count ++;
                    lock.notifyAll();
                    try {
                        if(count <100){

                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();


    }


}
