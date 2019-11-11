package com.wrh.math.review;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:25 2019/10/21 0021
 * @Modified By:
 */
public class Outter {
    static class Inner{
        static int paramOne = 5;
        static final int paramTwo = 5;
        static final int paramThree = new Integer(5);
    }

    public static void main(String[] args) {
        System.out.println(Inner.paramOne);
        System.out.println(Inner.paramTwo);
        System.out.println(Inner.paramThree);
    }
}
