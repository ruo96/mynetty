package com.wrh.proxy.jdkproxy;

/**
 * @author wuruohong
 * @Classname TestJdkProxy
 * @Description TODO
 * @Date 2020/12/18 15:11
 */
public class TestJdkProxy {
    public static void main(String[] args) {
        IUserDao target = new UserDao();
        System.out.println("目标对象： "  + target.getClass());
//        JDK动态生成了一个类去实现接口 使用jdk生成的动态代理的前提是目标类必须有实现的接口。
//        但这里又引入一个问题，如果某个类没有实现接口，就不能使用JDK动态代理，所以Cglib代理就是解决这个问题的。
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();
    }
}
