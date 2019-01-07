package com.wrh.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:18 2018/12/24 0024
 * @Modified By:
 */
public class ThreadName {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group,"t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("main thread belong group: " + mainThreadGroup);
        System.out.println("t1 and main belong the same group: " + (mainThreadGroup == t1.getThreadGroup()));
        System.out.println("t2 and main belong the same group: " + (mainThreadGroup == t2.getThreadGroup()));
        System.out.println("t2  belong the main group: " + (group == t2.getThreadGroup()));

        TimeUnit.SECONDS.sleep(5);
    }
}
