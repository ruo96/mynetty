package com.wrh.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:41 2019/11/29 0029
 * @Modified By:
 */
@Data
@Component
@ConfigurationProperties(prefix = "database")
@PropertySource(value = {"classpath:app1.properties"}/*,
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "app1.properties"*/)
public class TestConfiguration {

    private String ip;

    private int port;

    /*public TestConfiguration(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }*/
}
