package com.wrh.condition;

import com.wrh.elasticsearch.Student;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author wuruohong
 * @Classname JdbcTemplateCondition
 * @Description TODO
 * @Date 2020/9/3 20:12
 */
public class JdbcTemplateCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try{
            conditionContext.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
