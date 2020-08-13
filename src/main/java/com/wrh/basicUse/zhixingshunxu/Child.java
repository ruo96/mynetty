package com.wrh.basicUse.zhixingshunxu;

/**
 * @author wuruohong
 * @Classname Chile
 * @Description TODO
 * @Date 2020/7/2 11:38
 */
public class Child extends Base {
    static Instance staticInstance = new Instance("3---Child类的静态成员变量staticInstance");
    static  {
        System.out.println("4---Child类的静态代码块执行了");
    }
    Instance instance = new Instance("8---Child类的普通成员变量instance");
    {
        System.out.println("9----Child类的动态代码块执行了");
    }
    Child() {
        System.out.println("10---Child类的构造器方法执行了");
    }
    public static void main(String[] args) {
        Child child = new Child();
    }
}
