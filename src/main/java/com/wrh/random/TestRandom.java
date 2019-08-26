package com.wrh.random;

import java.util.Random;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:13 2019/6/4 0004
 * @Modified By:
 */
public class TestRandom {
    public static void main(String[] args) {
        Random i = new Random();
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());
        System.out.println(i.nextInt());

        int j = 100;
        System.out.println(j>>1);
        System.out.println(j>>>1);

        int k = -100;
        System.out.println(k>>1);
        System.out.println(k>>>1);
    }
}
