package com.wrh.spring.order;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/7 11:37
 */
@Component
public class TestOrder implements InitializingBean {
    static  {
        System.out.println("[TestOrder]>>> static run!" );
    }

    @PostConstruct
    public void post() {
        System.out.println("[TestOrder]>>> postconstruct run");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[TestOrder]>>> afterPropertiesSet run");
    }
}
