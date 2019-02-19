package com.wrh.thread.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:43 2019/2/19 0019
 * @Modified By:
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
        System.out.println(ai.incrementAndGet());
        System.out.println(ai.compareAndSet(3,5));
        System.out.println(ai.get());
    }
}
