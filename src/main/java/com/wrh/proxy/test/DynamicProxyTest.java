package com.wrh.proxy.test;

import com.wrh.proxy.IUserService;
import com.wrh.proxy.MyInvocationHandler;
import com.wrh.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:44 2018/9/4 0004
 * @Modified By:
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        IUserService target = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);

        //第一个参数是指定代理类的类加载器（我们传入当前测试类的类加载器）
        //第二个参数是代理类需要实现的接口（我们传入被代理类实现的接口，这样生成的代理类和被代理类就实现了相同的接口）
        //第三个参数是invocation handler，用来处理方法的调用。这里传入我们自己实现的handler
        IUserService proxyObject = (IUserService) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                target.getClass().getInterfaces(),handler);
        proxyObject.add("wrh1");
    }
}
