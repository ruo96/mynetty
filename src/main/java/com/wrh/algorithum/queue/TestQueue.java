package com.wrh.algorithum.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.mail.search.AddressTerm;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wuruohong
 * @Classname TestQueue
 * @Description TODO
 * @Date 2020/12/30 10:51
 */
@Slf4j
public class TestQueue {
    @Test
    public void Test14() {
        Queue<String> queue = new ArrayDeque<>();
        Queue<String> queue1 = new ArrayBlockingQueue<String>(16);
        queue.add("w1");
    }

    @Test
    public void Test25() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.offer("w1");
        queue.offer("w2");
        queue.offer("w5");
        queue.offer("w4");
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
