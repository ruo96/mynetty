package com.wrh.controller.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname ArrayPropertyConfig
 * @Description TODO
 * @Date 2022/1/14 10:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class AutoRefreshPropertyConfig {

    private String autoConfig;
}
