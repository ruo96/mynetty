package com.wrh.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname GPBeanFactoryPostProcessor
 * @Description TODO
 * @Date 2021/12/14 14:51
 */
@Slf4j
@Component
public class GPBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public GPBeanFactoryPostProcessor() {
        super();
        log.info("调用 BeanFactoryPostProcessor 实现类构造器");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        BeanDefinition bd = beanFactory.getBeanDefinition("author");
        bd.getPropertyValues().addPropertyValue("age", "16");
    }
}
