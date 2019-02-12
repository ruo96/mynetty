package com.wrh.thread.Singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:27 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonHungry {

    /*饿汉式, 开始即加载, 这个方法可以保证多线程下的唯一实例, 性能也比较高, 但是无法进行懒加载*/
    private byte[] data = new byte[1024];

    private static SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){}

    public static SingletonHungry getInstance(){
        return instance;
    }
}
