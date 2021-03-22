package com.wrh.annotate.annotation.decodeencode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuruohong
 * @Classname Decrypt
 * @Description TODO
 * @Date 2021/3/22 12:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
    /** 哪个接口方法添加了 @Encrypt 注解就对哪个接口的数据加密返回，哪个接口/参数添加了 @Decrypt 注解就对哪个接口/参数进行解密。*/
}
