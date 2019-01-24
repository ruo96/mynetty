package com.wrh.usestatic;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:55 2019/1/9 0009
 * @Modified By:
 */
public class TestStatic {

    static class Inder{
        static int paramOne = 5;
        static final int paramTwo = 5;
        static final int paramThree = new Integer(5);
    }

    public static void main(String[] args) {
        System.out.println(Inder.paramOne);
        System.out.println(Inder.paramTwo);
        System.out.println(Inder.paramThree);
    }
}
