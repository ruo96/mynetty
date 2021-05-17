package com.wrh.jvm;

/**
 * @author wuruohong
 * @Classname TestStackCall
 * @Description TODO
 * @Date 2021/5/10 11:01
 */
public class TestStackCall {
    public static void main(String[] args) {
        method1(10);
    }

    private static void method1(int x) {
        int y = x+1;
        Object m = method2();
        System.out.println(m.toString());
    }

    private static Object method2() {
        Object n = new Object();
        return n;
    }
}
