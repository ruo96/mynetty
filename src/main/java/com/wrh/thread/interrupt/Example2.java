package com.wrh.thread.interrupt;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname Example6
 * @Description TODO
 * @Date 2021/10/12 16:28
 */
public class Example2 extends Thread {
    volatile boolean stop = false;// 线程中断信号量

    public static void main(String args[]) throws Exception {
        Example2 thread = new Example2();
        System.out.println("Starting thread..." + LocalDateTime.now().toString());
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop..." + LocalDateTime.now().toString());
        // 设置中断信号量
        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping application..." + LocalDateTime.now().toString());
    }

    @Override
    public void run() {
        // 每隔一秒检测一下中断信号量
        while (!stop) {
            System.out.println("Thread is running..." + LocalDateTime.now().toString());
            long time = System.currentTimeMillis();
            /*
             * 使用while循环模拟 sleep 方法，这里不要使用sleep，否则在阻塞时会 抛
             * InterruptedException异常而退出循环，这样while检测stop条件就不会执行，
             * 失去了意义。
             */
            while ((System.currentTimeMillis() - time < 1000)) {}
        }
        System.out.println("Thread exiting under request..." + LocalDateTime.now().toString());
    }
}
