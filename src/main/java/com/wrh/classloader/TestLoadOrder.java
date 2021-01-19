package com.wrh.classloader;

import org.junit.Test;

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
//    public static void main(String[] args) {
//        TestLoadOrder testLoadOrder = new TestLoadOrder();
//    }


    /**
     * 类的主动引用（一定发生初始化）
     * 当虚拟机启动，先初始化main方法所有在类
     * new 一个类的对象
     * 调用类的静态成员（除了 final常量）和静态方法
     * 使用 java.lang.reflect包的方法对类进行反射调用
     * 当初始化一个类，如果其父类没有被初始化，则会先初始化它的父类
     *
     * 类的被动引用（不会发生初始化）
     * 当访问一个静态域时，只有真正的申明这个域的类才会被初始化，如：当通过子类引用父类的静态变量，不会导致子类初始化
     * 通过数组定义类引用，不会触发此类的初始化
     * 引用常量不会触发此类的初始化（常量在链接阶段就存入调用类的常量池了）
     * @throws ClassNotFoundException
     */
    @Test
    public void Test67() {
//当前类是哪个加载器
        ClassLoader loader = TestLoadOrder.class.getClassLoader();
        System.out.println(loader);

        // 获取系统类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        // 获取系统类加载器的父类加载器 -> 扩展类加载器
        ClassLoader parentClassLoader = classLoader.getParent();
        System.out.println(parentClassLoader);

        // 获取扩展类加载器的父类加载器 -> 根加载器（C、C++）
        ClassLoader superParentClassLoader = parentClassLoader.getParent();
        System.out.println(superParentClassLoader);

        // 测试JDK内置类是谁加载的
        ClassLoader loader2 = Object.class.getClassLoader();
        System.out.println(loader2);

    }
    
    @Test
    public void Test93() {
        // 如何获取类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
        
    }
}
