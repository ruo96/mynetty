package com.wrh.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * @author wuruohong
 * @Classname SecurityProperties
 * @Description TODO
 * @Date 2020/5/6 18:25
 */
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {
    private String user;

    private String password;

    private boolean passwordGenerated;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        if(!StringUtils.hasLength(password)) {
            return;
        }
        this.passwordGenerated = false;
        this.password = password;
    }
}
