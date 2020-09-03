package com.wrh.springbootdefinition;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author wuruohong
 * @Classname TestDefinition
 * @Description TODO
 * @Date 2020/9/3 18:45
 */
@Slf4j
public class TestDefinition {
    @Test
    public void Test14() {
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();

        AbstractBeanDefinition definition = new RootBeanDefinition(Student.class, 1,true);

        beanRegistry.registerBeanDefinition("student", definition);
        BeanFactory container = (BeanFactory) beanRegistry;
        Student student = (Student) container.getBean("student");
        System.out.println(student);


    }
}
