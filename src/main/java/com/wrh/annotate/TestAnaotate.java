package com.wrh.annotate;

import com.wrh.annotate.annotation.NeedDatav;
import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:05 2018/10/17 0017
 * @Modified By:
 */
@Component
@ConfigurationProperties("server")
public class TestAnaotate {

    public class info{

        public  int address;
        public  int port;

    }

    @Test
    public void Test24() {
        System.out.println("needDatav.annotationType().getName() = " + NeedDatav.class.getName());

    }
}
