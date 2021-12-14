package com.wrh.bean.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wuruohong
 * @Classname BeanLifeCycleTest
 * @Description TODO
 * @Date 2021/12/14 14:53
 */
@Slf4j
public class BeanLifeCycleTest {

    /**
     * 15:56:20.030 [main] INFO com.tom.lifecycle.GPBeanFactoryPostProcessor - 调用BeanFactoryPostProcessor实现类构造器！！
     * 15:56:20.045 [main] INFO com.tom.lifecycle.GPBeanFactoryPostProcessor - BeanFactoryPostProcessor调用postProcessBeanFactory方法
     * 15:56:20.046 [main] INFO com.tom.lifecycle.GPBeanPostProcessor - 调用BeanPostProcessor实现类构造器！！
     * 15:56:20.047 [main] INFO com.tom.lifecycle.GPInstantiationAwareBeanPostProcessor - 调用InstantiationAwareBeanPostProcessorAdapter实现类构造器！！
     * 15:56:20.051 [main] INFO com.tom.lifecycle.GPInstantiationAwareBeanPostProcessor - InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法
     * 15:56:20.052 [main] INFO com.tom.lifecycle.Author - 【构造器】调用Tom类的构造器实例化
     * 15:56:20.069 [main] INFO com.tom.lifecycle.GPInstantiationAwareBeanPostProcessor - InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法
     * 15:56:20.092 [main] INFO com.tom.lifecycle.Author - 【注入属性】address
     * 15:56:20.092 [main] INFO com.tom.lifecycle.Author - 【注入属性】age
     * 15:56:20.092 [main] INFO com.tom.lifecycle.Author - 【注入属性】name
     * 15:56:20.092 [main] INFO com.tom.lifecycle.Author - 【BeanNameAware接口】调用setBeanName方法
     * 15:56:20.092 [main] INFO com.tom.lifecycle.Author - 【BeanFactoryAware接口】调用setBeanFactory方法
     * 15:56:20.093 [main] INFO com.tom.lifecycle.GPBeanPostProcessor - BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改
     * 15:56:20.093 [main] INFO com.tom.lifecycle.Author - 【InitializingBean接口】调用afterPropertiesSet方法
     * 15:56:20.093 [main] INFO com.tom.lifecycle.Author - 【init-method】调用<bean>的init-method属性指定的初始化方法
     * 15:56:20.093 [main] INFO com.tom.lifecycle.GPBeanPostProcessor - BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改
     * 15:56:20.093 [main] INFO com.tom.lifecycle.GPInstantiationAwareBeanPostProcessor - InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法
     * 15:56:20.097 [main] INFO com.tom.lifecycle.BeanLifeCycleTest - ====== 初始化Spring容器成功 ========
     * 15:56:20.098 [main] INFO com.tom.lifecycle.BeanLifeCycleTest - Author(name=Tom, address=湖南长沙, age=16, beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@26653222: defining beans [beanPostProcessor,beanFactoryPostProcessor,instantiationAwareBeanPostProcessor,author]; root of factory hierarchy, beanName=author)
     * 15:56:20.098 [main] INFO com.tom.lifecycle.BeanLifeCycleTest - ====== 开始销毁Spring容器 ========
     * 15:56:20.099 [Thread-0] INFO com.tom.lifecycle.Author - 【DiposibleBean接口】调用destroy方法
     * 15:56:20.100 [Thread-0] INFO com.tom.lifecycle.Author - 【destroy-method】调用<bean>的destroy-method属性指定的初始化方法
     * @param args
     */
    public static void main(String[] args) {

        log.info("====== 开始初始化Spring容器 ========");

        ApplicationContext factory = new AnnotationConfigApplicationContext(AuthorLifeCycleConfig.class);

        log.info("====== 初始化Spring容器成功 ========");

        //获取Author实例
        Author author = factory.getBean("author", Author.class);

        log.info(author.toString());

        log.info("====== 开始销毁Spring容器 ========");

        ((AnnotationConfigApplicationContext) factory).registerShutdownHook();
    }
}
