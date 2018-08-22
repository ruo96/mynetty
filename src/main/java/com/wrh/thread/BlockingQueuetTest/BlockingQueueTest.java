package com.wrh.thread.BlockingQueuetTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:17 2018/8/22 0022
 * @Modified By:
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for(int i =0;i<2;i++){
            new Thread(){
                @Override
                public void run(){
                    while(true){
                        try {
                            Thread.sleep((long)(Math.random()*1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据!");

                            queue.put(1);

                            System.out.println(Thread.currentThread().getName() + "已经放了数据, " +
                                "队列目前有: " + queue.size() + "个数据!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据!");
                        System.err.print(queue.take());
                        System.out.println(Thread.currentThread().getName() + "已经取走数据, " +
                                "队列目前有: " + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
