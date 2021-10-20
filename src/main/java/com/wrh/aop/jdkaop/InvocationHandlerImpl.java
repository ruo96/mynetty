package com.wrh.aop.jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wuruohong
 * @Classname InvocationHandlerImpl
 * @Description TODO
 * @Date 2021/10/18 16:15
 */
public class InvocationHandlerImpl implements InvocationHandler {

    ShowService target;

    public InvocationHandlerImpl(ShowService showService) {
        this.target = showService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        getMoney();
        Object invoke = method.invoke(target, args);
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
