package com.wrh.bean;
import com.alibaba.fastjson.JSON;
import com.wrh.functionInterfaceTest.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Lists;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.MutablePropertySources;

/**
 * @author wuruohong
 * @Classname TestBean
 * @Description TODO
 * @Date 2020/6/10 10:28
 */
@Slf4j
public class TestBean implements BeanNameAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBean.class);

    private String name;

    @Test
    public void Test() {
        RealtimeReq req = new RealtimeReq();
        req.setGameBaseId(0);
        req.setDsEnd(LocalDate.now());
        req.setLoginType(0);
        req.setTourFlag(0);
        req.setSdkTypeId(0);
        req.setNowFlag("");
        req.setDsList(Lists.newArrayList());
        req.setGameIdList(Lists.newArrayList());
        req.setAllGameIdList(Lists.newArrayList());
        req.setMaxSlotNum(0);
        req.setMinSlotNum(0);
        req.setDs("");


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

    public static void main1(String[] args) {
        ClassPathXmlApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean = (TestBean) pathXmlApplicationContext.getBean("testBean");
        pathXmlApplicationContext.close();
    }

    public static void main2(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Student.class);
        context.getBean("w1");
    }

    @Test
    public void Test72() {
        ApplicationContext context = new ClassPathXmlApplicationContext("");
        context.getBean("id");

    }

    @Test
    public void Test109() {
        NormalBean bean = new NormalBean();

    }

    public static void main(String[] args) throws InterruptedException {
        NormalBean bean = new NormalBean();
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void Test121() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Map<String, Object> systemEnviroment = context.getEnvironment().getSystemEnvironment();
        System.out.println("systemEnviroment = " + systemEnviroment);

        System.out.println("====================================================");
        Map<String, Object> systemProperties = context.getEnvironment().getSystemProperties();
        System.out.println("systemProperties = " + systemProperties);

        System.out.println("====================================================");
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println("propertySources = " + propertySources);

        System.out.println("====================================================");
    }

}
