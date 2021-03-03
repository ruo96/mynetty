package com.wrh.gc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wuruohong
 * @Classname DirectMemoryOOM
 * @Description TODO
 * @Date 2021/1/23 17:36
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
