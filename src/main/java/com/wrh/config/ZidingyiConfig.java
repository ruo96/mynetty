package com.wrh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:57 2019/3/8 0008
 * @Modified By:
 */
@Data
@Component
@PropertySource("classpath:myproperty.yml")
@ConfigurationProperties
public class ZidingyiConfig {
    private String user;
}
