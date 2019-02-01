package com.wrh.thread.eventqueue;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:27 2019/2/1 0001
 * @Modified By:
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for(;;){
                try {
                    eventQueue.offer(new EventQueue.Event());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                try {
                    eventQueue.take();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
