package com.wrh.aop.jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wuruohong
 * @Classname JDKProxyDemo
 * @Description TODO
 * @Date 2021/10/18 16:20
 */
public class JDKProxyDemo {
    public static void main(String[] args) {
        // 1.创建被代理的具体类
        Star star = new Star("Eminem");
        // 2.获取对应的ClassLoader
        ClassLoader classLoader = star.getClass().getClassLoader();
        // 3.获取被代理对象实现的所有接口
        Class[] interfaces = star.getClass().getInterfaces();
        // 4.设置请求处理器，处理所有方法调用
        InvocationHandler invocationHandler = new InvocationHandlerImpl(star);

        /**
         * 5.根据上面提供的信息，创建代理对象 在这个过程中，
         *   a.JDK会通过根据传入的参数信息动态地在内存中创建和.class文件等同的字节码
         *   b.然后根据相应的字节码转换成对应的class，
         *   c.然后调用newInstance()创建实例
         */
        Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        ShowService showService = (ShowService)o;
        showService.sing("Mockingbird");
    }
}
