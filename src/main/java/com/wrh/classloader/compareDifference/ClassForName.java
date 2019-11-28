package com.wrh.classloader.compareDifference;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:00 2019/11/11 0011
 * @Modified By:
 */
public class ClassForName {

    //静态代码块
    static{
        System.out.println("执行了静态代码块");
    }

    //静态变量
    private static String staticField = staticMethod();

    //赋值静态变量的静态方法
    public static String staticMethod(){
        System.out.println("执行了静态方法");
        return "给静态字段赋值了";
    }
}
