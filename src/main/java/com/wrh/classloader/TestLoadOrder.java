package com.wrh.classloader;

/**
 * @author wuruohong
 * @Classname TestLoadOrder
 * @Description TODO
 * @Date 2020/12/23 11:31
 */
public class TestLoadOrder {
    public TestLoadOrder() {
        System.out.println("TestLoadOrder new instance");
    }

    static {
        System.out.println("TestLoadOrder static block");
    }

    {
        System.out.println("TestLoadOrder  block ！！！");
    }

    /**
     * 类的初始化的执行顺序
     *
     * 没有父类的情况：
     *
     * 类的静态属性
     * 类的静态代码块
     * 类的非静态属性
     * 类的非静态代码块
     * 构造方法
     *
     * 有父类的情况：
     *
     * 父类的静态属性
     * 父类的静态代码块
     * 子类的静态属性
     * 子类的静态代码块
     * 父类的非静态属性
     * 父类的非静态代码块
     * 父类构造方法
     * 子类非静态属性
     * 子类非静态代码块
     * 子类构造方法
     * @param args
     */
    public static void main(String[] args) {
        TestLoadOrder testLoadOrder = new TestLoadOrder();
    }
}
