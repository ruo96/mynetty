package com.wrh.spring.importest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wuruohong
 * @Classname ImportAnnotationInstance
 * @Description TODO
 * @Date 2022/1/5 11:06
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MynettyApplication.class)
public class ImportAnnotationInstance {

    @Autowired(required = false)
    ABeanDefinition aBeanDefinition;

    @Test
    public void invokeABeanDefinition() {
        aBeanDefinition.printName();
    }
}
