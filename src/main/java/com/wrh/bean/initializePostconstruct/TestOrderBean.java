package com.wrh.bean.initializePostconstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wuruohong
 * @Classname TestOrderBean
 * @Description TODO
 * @Date 2021/8/31 14:56
 */
@Slf4j
@Component
public class TestOrderBean implements InitializingBean {

    /** 这个较晚执行*/
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>> afterPropertiesSet here!!!!");
    }

    /**
     * 这个较早执行
     */
    @PostConstruct
    public void init() {
        System.out.println(">>> postconstruct here!!!");
    }
}
