package com.wrh.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:58 2019/6/27 0027
 * @Modified By:
 */
public class TestQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        System.out.println(queue.offer(1)+" ");
        System.out.println(queue.offer(2)+" ");
        System.out.println(queue.offer(3)+" ");
        System.out.println(queue.take()+" ");
        System.out.println(queue.size());
    }
}
