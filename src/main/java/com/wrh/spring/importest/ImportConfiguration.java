package com.wrh.spring.importest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author wuruohong
 * @Classname ImportConfiguration
 * @Description TODO
 * @Date 2022/1/5 11:04
 */
@Import({ABeanDefinition.class})
@Configuration
public class ImportConfiguration {
}
