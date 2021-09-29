package com.wrh.bean;

import com.wrh.bean.vo.Svc;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author wuruohong
 * @Classname SimpleTest
 * @Description TODO
 * @Date 2021/7/7 11:25
 */
@SpringBootTest
public class SimpleTest {
    /*@Autowired
//    @Qualifier("svcA")
//    @Resource(name = "svcA")
    Svc svc;*/

    private final Svc svc;

    @Autowired
    public SimpleTest(@Qualifier("svcB") Svc svc) {
         Assert.notNull(svc, "svc must not be null");
        this.svc = svc;
    }

    @Test
    public void rc(){
//        Assertions.assertNotNull(svc);
        svc.sayHello();
    }


}
