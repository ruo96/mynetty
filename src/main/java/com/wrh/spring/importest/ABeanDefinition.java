package com.wrh.spring.importest;

/**
 * @author wuruohong
 * @Classname ABeanDefinition
 * @Description TODO
 * @Date 2022/1/5 11:05
 */
public class ABeanDefinition {
    public String printName() {
        return "类名： " + Thread.currentThread().getStackTrace()[1].getClassName();
    }
}
