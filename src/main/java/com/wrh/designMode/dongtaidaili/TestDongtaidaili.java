package com.wrh.designMode.dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wuruohong
 * @Classname TestDongtaidaili
 * @Description TODO
 * @Date 2021/3/18 10:34
 */
public class TestDongtaidaili {
    public static void main(String[] args) {

        //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //被代理类
        final Student jack = new ComputerStudent("jack");
        /**
         * loader :定义代理类的类加载器，这里要代理的是jack,所以用jack的类加载器
         * interfaces :是一个接口类的集合，具体来说是代理类实现的接口的集合，也是被代理类实现的接口的集合；
         * h :代理类对象调用方法时需要用到的一个接口对象，在系统生成的代理类内部会用到它。
         *     Proxy.newProxyInstance为什么要穿入这3个参数？
         * //生成代理对象
         * Student jackProxy= (Student) Proxy.newProxyInstance(jack.getClass().getClassLoader(),
         * jack.getClass().getInterfaces(),
         * new InvocationHandler() );
         * 因为代理对象是基于自定义接口Student和jack类加载器代理出来的
         * 当代理对象调用方法时，会回调执行刚才new出来的InvocationHandler中的invoke()方法。
         */
        //生成代理对象
        Student jackProxy = (Student) Proxy.newProxyInstance(jack.getClass().getClassLoader(), jack.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before exam do something");
                        method.invoke(jack, args);
                        System.out.println("after exam do something");
                        return null;
                    }
                });

        /**
         * invoke()不是显示调用的，是在代理类中去调用的。
         * 比如调用exam()方法时， 该方法中会调用super.h.invoke(this, m3, null);，
         * 就是调用父类的h的invoke()，它的父类是Proxy，h是一个InvocationHandler对象；
         * 所以说当调用exam()方法时最后回调到刚才new出来的InvocationHandler的invoke方法
         */
        jackProxy.exam();
        jackProxy.study();
    }
}
