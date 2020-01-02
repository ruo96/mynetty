package com.wrh.thread.interrupt;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class ThreadA implements Runnable {

    private static volatile  boolean stopFlag = false;

    private int timeout;

    public ThreadA(int timeout) {
        this.timeout = timeout;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!stopFlag) {

            System.out.println(Thread.currentThread().getName() + "还在执行");
            TimeUnit.SECONDS.sleep(timeout);
            if( timeout == 5) {
                System.out.println("准备更改volatile");
                stopFlag = true;
            }
        }
    }
}
