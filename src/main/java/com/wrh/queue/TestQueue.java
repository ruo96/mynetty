package com.wrh.queue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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

    @Test
    public void Test23() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.add("w1");
        queue.add("w2");
        queue.add("w3");
        queue.add("w4");

        /*queue.stream().forEach(e->{
            System.out.println(e);
        });*/

        /*System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println("=="+queue.take());*/

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
