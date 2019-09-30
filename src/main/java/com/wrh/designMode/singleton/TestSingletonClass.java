package com.wrh.designMode.singleton;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:45 2019/9/30 0030
 * @Modified By:
 */
@Slf4j
public class TestSingletonClass {

    CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    @Test
    public void test(){


        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("waiting num: {}",cyclicBarrier.getNumberWaiting());
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    SingletonClass singletonClass = SingletonClass.getInstance();
                    log.info("线程:name [{}] group:[{}] classloader: [{}] 获取的单例class：{}",Thread.currentThread().getName(),Thread.currentThread().getThreadGroup(),Thread.currentThread().getContextClassLoader(),singletonClass);
                }
            });
            t1.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("waiting num: {}",cyclicBarrier.getNumberWaiting());
                        log.info("enter id: {}",Thread.currentThread().getId());
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    SingletonClass singletonClass = SingletonClass.getInstance();
                    log.info("time: [{}]线程:name [{}] group:[{}] classloader: [{}] 获取的单例class：{}",System.currentTimeMillis(),Thread.currentThread().getName(),Thread.currentThread().getThreadGroup(),Thread.currentThread().getContextClassLoader(),singletonClass);
                }
            });
            t1.start();
            TimeUnit.SECONDS.sleep(3);
        }


    }


}
