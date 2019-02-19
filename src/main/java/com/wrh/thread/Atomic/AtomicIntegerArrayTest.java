package com.wrh.thread.Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:48 2019/2/19 0019
 * @Modified By:
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1,2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));

        //不会影响原来的数组
        System.out.println(value[0]);
    }
}
