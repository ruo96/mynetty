package com.wrh.annotate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

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
}
