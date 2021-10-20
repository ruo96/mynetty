package com.wrh.thread.interrupt;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wuruohong
 * @Classname Example6
 * @Description TODO
 * @Date 2021/10/12 16:28
 */
public class Example3 extends Thread {
    public static void main(String args[]) throws Exception {
        Example3 thread = new Example3();
        System.out.println("Starting thread..." + LocalTime.now().toString());
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop..." + LocalTime.now().toString());
        thread.interrupt();// 等中断信号量设置后再调用
        Thread.sleep(3000);
        System.out.println("Stopping application..." + LocalTime.now().toString());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread running..." + LocalTime.now().toString());
            try {
                /*
                 * 如果线程阻塞，将不会去检查中断信号量stop变量，所 以thread.interrupt()
                 * 会使阻塞线程从阻塞的地方抛出异常，让阻塞线程从阻塞状态逃离出来，并
                 * 进行异常块进行 相应的处理
                 */
                Thread.sleep(1000);// 线程阻塞，如果线程收到中断操作信号将抛出异常
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted..." + LocalTime.now().toString());
                /*
                 * 如果线程在调用 Object.wait()方法，或者该类的 join() 、sleep()方法
                 * 过程中受阻，则其中断状态将被清除
                 */
                System.out.println(this.isInterrupted());// false

                //中不中断由自己决定，如果需要真真中断线程，则需要重新设置中断位，如果
                //不需要，则不用调用
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread exiting under request..." + LocalTime.now().toString());
    }
}
