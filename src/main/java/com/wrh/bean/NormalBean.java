package com.wrh.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wuruohong
 * @Classname NormalBean
 * @Description TODO
 * @Date 2021/4/28 18:07
 */
@Slf4j
@Component
public class NormalBean implements InitializingBean {

    public NormalBean(){
        log.info(">>> construct start");
    }

    @PostConstruct
    public void init(){
        log.info(">>> postconstruct start");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(">>> afterPropertiesSet start");
    }
}
