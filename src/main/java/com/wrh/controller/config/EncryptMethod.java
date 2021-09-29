package com.wrh.controller.config;

import java.lang.annotation.*;

/**
 * @author wuruohong
 * @Classname EncryptField
 * @Description TODO
 * @Date 2021/9/8 16:45
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptMethod {
    String[] type() default "ENCRYPT";
}
