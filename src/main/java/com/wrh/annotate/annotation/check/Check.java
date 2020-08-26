package com.wrh.annotate.annotation.check;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuruohong
 * @Classname Check
 * @Description 判断入参是否在规定范围中
 * @Date 2020/8/21 17:56
 */
@Target({ElementType.FIELD}) //只允许用来类的字段上
@Retention(RetentionPolicy.RUNTIME) // 注解保留在程序运行期间， 此时可以通过反射获取定义在类上的所有注解
@Constraint(validatedBy = ParamConstraintValidated.class)
public @interface Check {

    /**
     * 合法的参数值
     * @return
     */
    String[] paramValues();

    /**
     * 提示信息
     * @return
     */
    String message() default "参数不为指定值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
