package com.wrh.spring.container;

import com.wrh.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wuruohong
 * @Classname PersonService
 * @Description TODO
 * @Date 2021/5/7 15:45
 */

/**
 * 一 如何获取spring容器对象
 * 1.实现BeanFactoryAware接口
 */
//@Service 用的时候加上
public class PersonService2 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void add() {
        Person person = (Person) applicationContext.getBean("person");
    }


}
