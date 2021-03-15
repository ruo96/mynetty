package com.wrh.bean;

import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

import javax.swing.*;

/**
 * @author wuruohong
 * @Classname TestBeanWrapper
 * @Description TODO
 * @Date 2021/3/15 12:04
 */
@Slf4j
public class TestBeanWrapper {

    //BeanWrapper 是 Spring 中提供一个工具，使用它可以修改一个对象的属性
    @Test
    public void Test14() {
        Student s = new Student();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(s);
        beanWrapper.setPropertyValue("name","w1");

        PropertyValue pv = new PropertyValue("id",1);
        beanWrapper.setPropertyValue(pv);
        System.out.println("s = " + s);

    }
}
