package com.wrh.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:40 2019/8/26 0026
 * @Modified By:
 */
@Slf4j
public class TestDifferentBetween {
    @Test
    public void test1(){
        try {
            Class clazz1 = Class.forName("com.wrh.classloader.ClassForName");
            log.info("======================上面是forName的加载过程，下面是classLoader的加载过程==================");
            Class clazz2 = ClassLoader.getSystemClassLoader().loadClass("com.wrh.classloader.ClassForName");

            log.info("===> clazz1 : {}",clazz1);
            log.info("===> clazz2 : {}",clazz2);
            log.info("===> clazz1.getName : {}",clazz1.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        log.info("===> 从结果中可以看出，Class.forName加载类是将类进行了初始化， 而ClassLoader的loadClass并没有对类进行初始化，只是把类加载到虚拟机中");
        log.info("===> 在spring的ioc的实现就是用的ClassLoader， 而jdbc里面是用的Class.forName，因为JDBC规范中要求Driver类必须向DriverManager注册自己");
    }
}
