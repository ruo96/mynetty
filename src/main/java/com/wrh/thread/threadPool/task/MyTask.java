package com.wrh.thread.threadPool.task;

import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname MyTask
 * @Description TODO
 * @Date 2021/12/20 14:33
 */
public class MyTask implements  Runnable {

    private String id;

    public MyTask(String id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + "=====" + id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
