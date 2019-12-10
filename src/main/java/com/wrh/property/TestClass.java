package com.wrh.property;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:43 2019/11/29 0029
 * @Modified By:
 */
@Slf4j
@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@SpringBootTest
public class TestClass {

    @Autowired
    TestConfiguration testConfiguration;

    @Test
    public void t1est(){
        String ip = testConfiguration.getIp();
        int port = testConfiguration.getPort();
        log.info("{}{}",ip,port);
    }

}
