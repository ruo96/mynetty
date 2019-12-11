package com.wrh.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:32 2019/12/11 0011
 * @Modified By:
 */
public class MoneyCountInvocationHandler implements InvocationHandler {

    private final Object target;

    private BigDecimal moneyCount;
    private BigDecimal addAmount;

    public MoneyCountInvocationHandler(Object target) {
        this.target = target;
        this.moneyCount = BigDecimal.valueOf(0.0);
        this.addAmount = BigDecimal.valueOf(0.7);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(target, args);
        moneyCount = moneyCount.add(addAmount);
        System.out.println("花费总数： " + moneyCount.toString() + "元");
        return result;
    }
}
