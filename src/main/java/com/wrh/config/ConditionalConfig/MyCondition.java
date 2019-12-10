package com.wrh.config.ConditionalConfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:41 2019/12/2 0002
 * @Modified By:
 */
public class MyCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return false;
    }
}
