package com.wrh.basicUse.zhixingshunxu;

/**
 * @author wuruohong
 * @Classname Zhixingshunxu
 * @Description TODO
 * @Date 2020/7/2 11:36
 */
public class Base {
    /**
     * 在Java中，类中元素的初始化顺序 初始化原则如下：
     * 先初始化静态部分，再初始化动态部分；
     * 先初始化父类部分，再初始化子类部分；
     * 先初始化变量，再初始化代码块和构造器。
     * 依照这个规则，可以得出的总体顺序是：
     * 父类静态成员变量加载（第一次加载时才会加载）
     * 父类的静态代码块（第一次加载时才会加载）
     * 子类的静态成员变量加载（第一次加载时才会加载）
     * 子类的静态代码块（第一次加载时才会加载）
     * 父类的普通成员变量
     * 父类的动态代码块
     * 父类的构造器
     * 子类的普通成员变量
     * 子类的动态代码块
     * 子类的构造器
     *
     * 输出结果如下：
     * 1---Base类的静态成员变量staticInstance进行了初始化
     * 2---Base类的静态代码块执行了
     * 3---Child类的静态成员变量staticInstance进行了初始化
     * 4---Child类的静态代码块执行了
     * 5---Base类的普通成员变量instance进行了初始化
     * 6---Base类的动态代码块执行了
     * 7---Base类的构造器方法执行了
     * 8---Child类的普通成员变量instance进行了初始化
     * 9----Child类的动态代码块执行了
     * 10---Child类的构造器方法执行了
     */
    static Instance staticInstance = new Instance("1---Base类的静态成员变量staticInstance");
    static  {
        System.out.println("2---Base类的静态代码块执行了");
    }
    Instance instance = new Instance("5---Base类的普通成员变量instance");
    {
        System.out.println("6---Base类的动态代码块执行了");
    }
    Base() {
        System.out.println("7---Base类的构造器方法执行了");
    }
}
