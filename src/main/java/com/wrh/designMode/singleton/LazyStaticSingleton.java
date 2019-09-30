package com.wrh.designMode.singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:10 2019/9/30 0030
 * @Modified By:
 */
public class LazyStaticSingleton {

    /**
     * 静态内部类在调用时才会进行初始化，因此是懒汉式的，LazyStaticSingleton lazyStaticSingleton = new LazyStaticSingleton();
     * 看似是饿汉式的，但是只有调用getLazyStaticSingleton时才会进行初始化，线程安全由ClassLoad保证，不用思考怎么加锁
     *
     * 前面几种方式实现单例的方式虽然各有优缺点，但是基本实现了单例线程安全的要求。但是总有人看不惯单例模式勤俭节约的作用，对它进行攻击。
     * 对它进行攻击无非就是创建不只一个类，java中创建对象的方式有new、clone、序列化、反射。
     * 构造函数私有化不可能通过new创建对象、同时单例类没有实现Cloneable接口无法通过clone方法创建对象，那剩下的攻击只有反射攻击和序列化攻击了
     */

    /**
     * 静态内部类
     * */
    private static class LazyStaticSingletonHolder {
        private static LazyStaticSingleton lazyStaticSingleton = new LazyStaticSingleton();
    }

    /**
     * 构造函数私有化
     * */
    private LazyStaticSingleton() {
    }

    public static LazyStaticSingleton getLazyStaticSingleton() {
        return LazyStaticSingletonHolder.lazyStaticSingleton;
    }
}
