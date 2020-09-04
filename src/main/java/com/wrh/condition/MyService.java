package com.wrh.condition;

import org.springframework.context.annotation.Conditional;

/**
 * @author wuruohong
 * @Classname MyService
 * @Description TODO
 * @Date 2020/9/3 20:14
 */
@Conditional(JdbcTemplateCondition.class)
public class MyService {
}
