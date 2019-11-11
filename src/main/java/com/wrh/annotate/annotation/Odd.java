package com.wrh.annotate.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:33 2019/11/11 0011
 * @Modified By:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Odd {
    String message() default "age must be odd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
