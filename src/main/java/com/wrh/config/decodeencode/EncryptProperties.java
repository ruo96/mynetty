package com.wrh.config.decodeencode;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuruohong
 * @Classname EncryptProperties
 * @Description TODO
 * @Date 2021/3/22 12:03
 */
//@Configuration
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    /** key 是 16 位字符串*/
    private final static String DEFAULT_KEY = "www.itboyhub.com";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
