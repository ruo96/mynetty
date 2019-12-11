package com.wrh.aop.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:35 2019/12/11 0011
 * @Modified By:
 */
@Slf4j
public class TestAop {
    @Test
    public void test1(){
       SMSService smsService = new SMSServiceImpl();
       smsService = (SMSService) Proxy.newProxyInstance(TestAop.class.getClassLoader(),
                                            new Class[]{SMSService.class},
                                            new MoneyCountInvocationHandler(smsService));
        for (int i = 0; i < 10; i++) {

            smsService.sendMessage();
        }
    }

    @Test
    public void test2(){
        BigDecimal a = BigDecimal.valueOf(1.2);
        BigDecimal b = BigDecimal.valueOf(1.2);
        log.info("a+b: {}",a.add(b));
    }
}
