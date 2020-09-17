package com.wrh.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:31 2018/8/20 0020
 * @Modified By:
 */
@Slf4j
public class TestScheduledExecutor {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        /*这个就无穷无尽了, 会OOM的*/
        ExecutorService pool = Executors.newCachedThreadPool();
        /*为什么这个不能运行呢*/
//        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5,new BasicThreadFactory.Builder().namingPattern("example-scheduled-pool-%d").daemon(true).build());
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();
        Thread t6 = new MyThread();
        Thread t7 = new MyThread();
        Thread t8 = new MyThread();
        Thread t9 = new MyThread();
        Thread t10 = new MyThread();

        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.execute(t5);
        service.execute(t6);
        service.execute(t7);
        service.execute(t8);
        service.execute(t9);
        service.execute(t10);

        service.shutdown();
    }

    @Test
    public void Test49() throws InterruptedException {
        ScheduledExecutorService timer = Executors.newScheduledThreadPool(3);
        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info(">>> once");
            }
        }, 10, 30,TimeUnit.MILLISECONDS);

        TimeUnit.SECONDS.sleep(10);

    }
}
