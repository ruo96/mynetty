package com.wrh.bean.lifecycle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author wuruohong
 * @Classname Author
 * @Description TODO
 * @Date 2021/12/14 14:44
 */
@Slf4j
@Data
public class Author implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;

    private String address;

    private int age;

    private BeanFactory beanFactory;

    private String beanName;

    public Author() {
        log.info("【构造器】调用Tom类的构造器实例化");
    }

    public void setName(String name) {
        log.info("【注入属性】name");
        this.name = name;
    }

    public void setAddress(String address) {
        log.info("【注入属性】address");
        this.address = address;
    }

    public void setAge(int age) {
        log.info("【注入属性】age");
        this.age = age;
    }

    // 实现BeanFactoryAware接口的方法
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("【BeanFactoryAware接口】调用setBeanFactory方法");
        this.beanFactory = beanFactory;
    }

    // 实现BeanNameAware接口的方法
    @Override
    public void setBeanName(String beanName) {
        log.info("【BeanNameAware接口】调用setBeanName方法");
        this.beanName = beanName;
    }

    // 实现DiposibleBean接口的方法
    @Override
    public void destroy() throws Exception {
        log.info("【DiposibleBean接口】调用destroy方法");
    }

    // 实现InitializingBean接口的方法
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("【InitializingBean接口】调用afterPropertiesSet方法");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void beanInit() {
        log.info("【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void beanDestory() {
        log.info("【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }
}
