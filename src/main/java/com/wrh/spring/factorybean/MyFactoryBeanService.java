package com.wrh.spring.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author wuruohong
 * @Classname MyFactoryBeanService
 * @Description TODO
 * @Date 2021/5/7 15:53
 */
public class MyFactoryBeanService implements BeanFactoryAware {

    /**
     * BeanFactory：spring容器的顶级接口，管理bean的工厂。
     * FactoryBean：并非普通的工厂bean，它隐藏了实例化一些复杂Bean的细节，给上层应用带来了便利。
     */
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * getBean("myFactoryBean");获取的是MyFactoryBeanService类中getObject方法返回的对象，
     *
     * getBean("&myFactoryBean");获取的才是MyFactoryBean对象。
     */
    public void test() {
        Object myFactoryBean = beanFactory.getBean("myFactoryBean");
        System.out.println("myFactoryBean = " + myFactoryBean);
        Object myFactoryBean1 = beanFactory.getBean("&myFactoryBean");
        System.out.println("myFactoryBean1 = " + myFactoryBean1);
    }
}
