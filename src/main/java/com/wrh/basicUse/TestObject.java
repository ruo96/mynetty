package com.wrh.basicUse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuruohong
 * @Classname TestObject
 * @Description TODO
 * @Date 2020/11/26 11:48
 */
@Slf4j
public class TestObject {

    @Test
    public void Test15() {
        Object o = new Object();
        log.info("layout: {}",ClassLayout.parseInstance(o).toPrintable());
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

    }
}
