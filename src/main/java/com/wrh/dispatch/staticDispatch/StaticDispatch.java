package com.wrh.dispatch.staticDispatch;

/**
 * @author wuruohong
 * @Classname StaticDispatch
 * @Description TODO
 * @Date 2021/3/15 15:14
 */
public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    /**
     * Human就是变量的“静态类型”（Static Type），或者叫“外观类型”（Apparent Type）；
     * Man就是变量的“实际类型”（Actual Type）或者叫“运行时类型”（Runtime Type）
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
