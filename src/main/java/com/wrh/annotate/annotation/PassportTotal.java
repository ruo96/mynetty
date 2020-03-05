package com.wrh.annotate.annotation;

import java.lang.annotation.*;

/**
 * @author wuruohong
 * @Classname Passport
 * @Description TODO
 * @Date 2020/2/27 16:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PassportTotal {
}
