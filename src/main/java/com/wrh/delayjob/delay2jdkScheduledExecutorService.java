package com.wrh.delayjob;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author wuruohong
 * @Classname delay2jdk
 * @Description Java API 实现延迟任务
 *
 * Java API 提供了两种实现延迟任务的方法：DelayQueue 和 ScheduledExecutorService。
 * @Date 2020/4/16 19:04
 */
public class delay2jdkScheduledExecutorService {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序启动时间:" + LocalDateTime.now());
        scheduledExecutorServiceTask();
        TimeUnit.SECONDS.sleep(50);
    }

    private static void scheduledExecutorServiceTask() {
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("delay-task-schedule-pool-%d").daemon(true).build());
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务： "+",执行时间： "+ LocalDateTime.now());
            }
        },2,2, TimeUnit.SECONDS);
    }

}
