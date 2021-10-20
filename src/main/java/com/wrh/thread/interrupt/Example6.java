package com.wrh.thread.interrupt;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname Example6
 * @Description
 * 一、没有任何语言方面的需求一个被中断的线程应该终止。中断一个线程只是为了引起该线程的注意，被中断线程可以决定如何应对中断。
 *
 * 二、对于处于sleep，join等操作的线程，如果被调用interrupt()后，会抛出InterruptedException，然后线程的中断标志位会由true重置为false，因为线程为了处理异常已经重新处于就绪状态。
 *
 * 三、不可中断的操作，包括进入synchronized段以及Lock.lock()，inputSteam.read()等，调用interrupt()对于这几个问题无效，因为它们都不抛出中断异常。如果拿不到资源，它们会无限期阻塞下去。
 *
 * Java的中断是一种协作机制。也就是说调用线程对象的interrupt方法并不一定就中断了正在运行的线程，它只是要求线程自己在合适的时机中断自己。
 * 每个线程都有一个boolean的中断状态（这个状态不在Thread的属性上），interrupt方法仅仅只是将该状态置为true。
 *
 *
 * @Date 2021/10/12 16:28
 */
public class Example6 extends Thread {
    volatile ServerSocket socket;

    public static void main(String args[]) throws Exception {
        Example6 thread = new Example6();
        System.out.println("Starting thread..." + LocalDateTime.now().toString());
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop..."+ LocalDateTime.now().toString());
        Thread.currentThread().interrupt();// 再调用interrupt方法
        thread.socket.close();// 再调用close方法
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("Stopping application..."+ LocalDateTime.now().toString());
    }

    @Override
    public void run() {
        try {
            socket = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("Could not create the socket..."+ LocalDateTime.now().toString());
            return;
        }
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Waiting for connection..."+ LocalDateTime.now().toString());
            try {
                socket.accept();
            } catch (IOException e) {
                System.out.println("accept() failed or interrupted..."+ LocalDateTime.now().toString());
                Thread.currentThread().interrupt();//重新设置中断标示位
            }
        }
        System.out.println("Thread exiting under request..."+ LocalDateTime.now().toString());
    }
}
