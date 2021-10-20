package com.wrh.aop.cglibaop;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wuruohong
 * @Classname MethodInterceptorImpl
 * @Description cglib 创建某个类A的动态代理类的模式是：
 *
 * 查找A上的所有非final 的public类型的方法定义；
 * 将这些方法的定义转换成字节码；
 * 将组成的字节码转换成相应的代理的class对象；
 * 实现 MethodInterceptor接口，用来处理对代理类上所有方法的请求（这个接口和JDK动态代理InvocationHandler的功能和角色是一样的）
 * @Date 2021/10/18 16:37
 */
public class MethodInterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        getMoney();

        Object invoke = methodProxy.invokeSuper(o,objects);
        writeReceipt();
        return invoke;
    }

    private void getMoney() {
        System.out.println("get money");
    }

    private void writeReceipt() {
        System.out.println("write receipt");
    }
}
