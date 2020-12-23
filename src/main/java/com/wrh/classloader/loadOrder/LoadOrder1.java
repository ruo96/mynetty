package com.wrh.classloader.loadOrder;

/**
 * @author wuruohong
 * @Classname LoadOrder1
 * @Description TODO
 * @Date 2020/12/23 11:58
 */
public class LoadOrder1 {
    private static LoadOrder1 singleton = new LoadOrder1();

    private LoadOrder1() {
        System.out.println("Singleton new instance");
    }

    public static void forTest() {

    }

    static {
        System.out.println("Singleton static block");
    }

    {
        System.out.println("Singleton  block ！！！ ");
    }

    /**
     * Singleton  block ！！！
     * Singleton new instance
     * Singleton static block
     *
     * new 会引起 Singleton 的初始化。需要执行 Singleton构造函数里面的内容。但是又因为非static初始化块，这里面的代码在创建java对象实例时执行，而且在构造器之前！！！
     *
     * @param args
     */
    public static void main(String args[]){
        LoadOrder1.forTest();
    }
}
