package com.wrh.thread.basicUse;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wuruohong
 * @Classname QueueThread
 * @Description TODO
 * @Date 2020/6/18 20:04
 */
public class QueueThread implements Runnable {

    private LinkedBlockingQueue<String> queue;

    public QueueThread(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            try {
                queue.put(i + "-");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
