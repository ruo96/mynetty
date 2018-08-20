package com.wrh.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:31 2018/8/20 0020
 * @Modified By:
 */
@Slf4j
public class TestExecutor  {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        /*为什么这个不能运行呢*/
//        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5,new BasicThreadFactory.Builder().namingPattern("example-scheduled-pool-%d").daemon(true).build());
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.execute(t5);

        service.shutdown();
    }
}
