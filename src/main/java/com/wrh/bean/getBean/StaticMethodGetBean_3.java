package com.wrh.bean.getBean;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname StaticMethodGetBean_3
 * @Description 获取bean的三种方式之一
 * @Date 2021/10/27 16:54
 */
@Component
public class StaticMethodGetBean_3<T> implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        StaticMethodGetBean_3.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext != null?applicationContext.getBean(clazz):null;
    }
}
