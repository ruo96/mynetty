package com.wrh.classloader;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:37 2019/8/26 0026
 * @Modified By:
 */
@Slf4j
public class ClassForName {
    static {
        log.info("===> 执行了静态代码块");
    }

    private static String staticField = staticMethod();

    private static String staticMethod() {
        log.info("===> 执行了静态方法");
        return "给静态字段赋值了";
    }
}
