package com.wrh.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname GPBeanPostProcessor
 * @Description ApplicationContext 可以在BeanDefinition中自动检测到实现了BeanPostProcessor的Bean，
 * 并且把这些Bean应用于随后的Bean创建。普通的BeanFactory允许对后处理器进行程序化注册，
 * 通过工厂应用于所有Bean创建。BeanPostProcessor接口中主要有两个方法：
 * | 方法名 | 解释 |
 * | -------- | -------- |
 * | postProcessBeforeInitialization | 在Bean实例化回调（例如InitializingBean的afterPropertiesSet 或者一个定制的init-method）之前应用此BeanPostProcessor |
 * | postProcessAfterInitialization | 在bean实例化回调（例如InitializingBean的afterPropertiesSet 或者一个定制的init-method）之后应用此BeanPostProcessor |
 * @Date 2021/12/14 16:07
 */
@Slf4j
@Component
public class GPBeanPostProcessor implements BeanPostProcessor {

    public GPBeanPostProcessor(){
        log.info("调用BeanPostProcessor实现类构造器！！");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        log.info("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        log.info("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改");
        return o;
    }

}
