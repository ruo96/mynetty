package com.wrh.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:31 2018/8/20 0020
 * @Modified By:
 */
@Slf4j
public class TestExecutor  {

    public static void main(String[] args) {


        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.schedule(t4,10, MILLISECONDS);
        pool.schedule(t5,10, MILLISECONDS);

        pool.shutdown();
    }
}
