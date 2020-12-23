package com.wrh.enums;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname Enums
 * @Description TODO
 * @Date 2020/12/21 14:40
 */
public class Enums {
    private static Random rand = new Random();

    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
