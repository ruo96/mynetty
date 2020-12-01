package com.wrh.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuruohong
 * @Classname TestBean
 * @Description TODO
 * @Date 2020/6/10 10:28
 */
@Slf4j
public class TestBean implements BeanNameAware {

    private String name;

    @Test
    public void Test() {

    }

    /**
     * 实现类上的override方法
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("调用BeanNameAware中的setName赋值");
    }

    public TestBean(){}

    /**
     * 属性赋值
     * @param name
     */
    public void setName(String name) {
        System.out.println("设置对象属性setName()..");
        this.name = name;
    }

    /**
     * Bean初始化
     */
    public void initBeanPerson() {
        System.out.println("初始化Bean");
    }

    /**
     * Bean方法使用：说话
     */
    public void speak() {
        System.out.println("使用Bean的Speak方法");
    }

    /**
     * 销毁Bean
     */
    public void destroyBeanPerson() {
        System.out.println("销毁Bean");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean = (TestBean) pathXmlApplicationContext.getBean("testBean");
        pathXmlApplicationContext.close();
    }

    @Test
    public void Test72() {
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        context.getBean("id");

    }

}
