package com.wrh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:41 2018/9/4 0004
 * @Modified By:
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备向数据库中插入数据");
        Object returnValue = method.invoke(target,args);
        System.out.println("插入完毕!");
        return returnValue;
    }
}
