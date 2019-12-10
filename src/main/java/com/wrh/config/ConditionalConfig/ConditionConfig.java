package com.wrh.config.ConditionalConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:43 2019/12/2 0002
 * @Modified By:
 */
@Configuration
@Conditional(MyCondition.class)
//@ConditionalO
public class ConditionConfig {
    @Bean
    public ConditionBean conditionBean() {
        return new ConditionBean();
    }
}
