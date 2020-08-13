package com.wrh.annotate.annotation;

import java.lang.annotation.*;

/**
 * Created by gairuyi on 1/25/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConcurrentRateLimiter {
    String value() default "";
}
