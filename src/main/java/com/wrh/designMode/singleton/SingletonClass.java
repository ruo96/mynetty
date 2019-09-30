package com.wrh.designMode.singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:42 2019/9/30 0030
 * @Modified By:
 */
public class SingletonClass {

    /**
     * 为什么需要对lazyDoubleCheckSingleton添加volatile修饰符
     *
     * 因为lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();不是原子性的，分为三步：
     *
     * 1. 为lazyDoubleCheckSingleton分配内存
     * 2. 调用构造函数进行初始化
     * 3. 将lazyDoubleCheckSingleton对象指向分配的内存【执行完这步lazyDoubleCheckSingleton将不为null】
     * 为了提高程序的运行效率，编译器会进行一个指令重排，步骤2和步骤三进行了重排，线程1先执行了步骤一和步骤三，
     * 执行完后，lazyDoubleCheckSingleton不为null，此时线程2执行到if (lazyDoubleCheckSingleton == null)，
     * 线程2将可能直接返回未正确进行初始化的lazyDoubleCheckSingleton对象。
     *
     * 出错的原因主要是lazyDoubleCheckSingleton未正确初始化完成【写】，但是其他线程已经读取lazyDoubleCheckSingleton的值【读】，
     * 使用volatile可以禁止指令重排序，通过内存屏障保证写操作之前不会调用读操作【执行if (lazyDoubleCheckSingleton == null)】
     *
     * 缺点：
     *
     * 为了保证线程安全，代码不够优雅过于臃肿
     */


    private static volatile SingletonClass singletonClass = null;

    private SingletonClass(){}

    public static SingletonClass getInstance(){
        if(singletonClass == null){
            synchronized (SingletonClass.class){
                if(singletonClass == null){
                    singletonClass = new SingletonClass();
                }
            }
        }
        return singletonClass;
    }
}
