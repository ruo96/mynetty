package com.wrh.classloader.compareDifference;

import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:03 2019/11/11 0011
 * @Modified By:
 */
public class TestDifference {


    /**
     * 根据运行结果得出 Class.forName 加载类是将类进了初始化，
     * 而 ClassLoader 的 loadClass 并没有对类进行初始化，只是把类加载到了虚拟机中。
     *
     * 应用场景
     *
     * 在我们熟悉的 Spring 框架中的 IOC 的实现就是使用的 ClassLoader。     *
     * 而在我们使用 JDBC 时通常是使用 Class.forName() 方法来加载数据库连接驱动。
     * 这是因为在 JDBC 规范中明确要求 Driver(数据库驱动)类必须向 DriverManager 注册自己。
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void test() throws ClassNotFoundException {
        Class.forName("com.wrh.classloader.compareDifference.ClassForName");
        System.out.println("=====分隔符（上面是class.forname的加载过程， 下面是classloader的加载过程）=====");
        ClassLoader.getSystemClassLoader().loadClass("com.wrh.classloader.compareDifference.ClassForName");
    }
}
