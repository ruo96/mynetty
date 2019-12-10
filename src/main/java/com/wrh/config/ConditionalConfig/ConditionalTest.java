package com.wrh.config.ConditionalConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:44 2019/12/2 0002
 * @Modified By:
 */
public class ConditionalTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ConditionBean bean = context.getBean(ConditionBean.class);
        bean.sayHi();
    }
}
