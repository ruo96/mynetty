package com.wrh.springbootdefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @author wuruohong
 * @Classname MyBeanFactoryPostProcessor
 * @Description TODO
 * @Date 2020/9/3 19:27
 */

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
    /**
     * 容器启动阶段
     * 在容器的启动阶段，BeanFactoryPostProcessor允许我们在容器实例化相应对象之前，
     * 对注册到容器的BeanDefinition所保存的信息做一些额外的操作，比如修改bean定义的某些属性或者增加其他信息等。
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        configurableListableBeanFactory.getBean("student");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
