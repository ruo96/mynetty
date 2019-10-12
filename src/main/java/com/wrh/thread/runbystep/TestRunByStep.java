package com.wrh.thread.runbystep;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Created by wrh
 * @Description: 线程顺序执行
 * @Date: Created in 上午 10:41 2019/10/12 0012
 * @Modified By:
 */
@Slf4j
public class TestRunByStep {

    /**
     * 第一种方法 使用join
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        T11 t1 = new T11();
        T22 t2 = new T22();
        T33 t3 = new T33();
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();

    }

    class T11 extends Thread {
        public void run() {
            System.out.println("in T1");
        }
    }

    class T22 extends Thread {
        public void run() {
            System.out.println("in T2");
        }
    }

    class T33 extends Thread {
        public void run() {
            System.out.println("in T3");
        }
    }


    /**
     * 使用单线程池来执行
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 1");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 2");
                try {
                    t1.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");
        final Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run 3");
                try {
                    t2.join(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");
        // method1
        //t1.start();
        //t2.start();
        //t3.start();

//        method 2 使用 单个任务的线程池来实现。保证线程的依次执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.shutdown();


    }

}
