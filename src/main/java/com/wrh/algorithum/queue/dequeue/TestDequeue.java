package com.wrh.algorithum.queue.dequeue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author wuruohong
 * @Classname TestDequeue
 * @Description TODO
 * @Date 2020/12/30 10:44
 */
@Slf4j
public class TestDequeue {
    @Test
    public void Test14() {
        Deque<String> deque = new ArrayDeque<>();
        deque.add("w1");
        System.out.println(deque);
        System.out.println(deque.peek());
        deque.pop();
        System.out.println(deque);

    }
}
