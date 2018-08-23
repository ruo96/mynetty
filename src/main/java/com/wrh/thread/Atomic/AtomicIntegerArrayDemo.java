package com.wrh.thread.Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:15 2018/8/23 0023
 * @Modified By:
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                arr.getAndIncrement(i%arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for(int k = 0;k<10;k++){
            ts[k] = new Thread(new AddThread());
        }

        for(int k = 0; k<10; k++){
            ts[k].start();
        }
        for(int k = 0; k<10; k++){
            ts[k].join();
        }
        System.out.println(arr);
    }
}
