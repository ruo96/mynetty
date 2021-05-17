package com.wrh.spring.container;

import com.wrh.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

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
//@Service
public class PersonService implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void add() {
        Person person = (Person) beanFactory.getBean("person");
    }
}
