package com.wrh.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wuruohong
 * @Classname UnsafeUtils
 * @Description TODO
 * @Date 2021/10/19 10:46
 */
public class UnsafeUtils {

    public static final Unsafe unsafe = getUnsafe();

    public static sun.misc.Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return  (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
