package com.wrh.aspectj;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:57 2019/11/25 0025
 * @Modified By:
 */
public class Student implements Person {
    @Override
    public void say() {
        System.out.println("这是一个苦逼的程序员");
    }
}
