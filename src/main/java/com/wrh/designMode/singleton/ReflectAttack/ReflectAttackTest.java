package com.wrh.designMode.singleton.ReflectAttack;

import com.wrh.designMode.singleton.LazyDoubleCheckSingleton;
import com.wrh.designMode.singleton.LazyLockSingleton;
import com.wrh.designMode.singleton.LazyStaticSingleton;
import com.wrh.designMode.singleton.LazySynchronizeSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:12 2019/9/30 0030
 * @Modified By:
 */
public class ReflectAttackTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //静态内部类
        LazyStaticSingleton lazyStaticSingleton = LazyStaticSingleton.getLazyStaticSingleton();
        //通过反射创建LazyStaticSingleton
        Constructor<LazyStaticSingleton> constructor = LazyStaticSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazyStaticSingleton lazyStaticSingleton1 = constructor.newInstance();
        //打印结果为false，说明又创建了一个新对象
        System.out.println(lazyStaticSingleton == lazyStaticSingleton1);

        //synchronize
        LazySynchronizeSingleton lazySynchronizeSingleton = LazySynchronizeSingleton.getLazySynchronizeSingleton();
        Constructor<LazySynchronizeSingleton> lazySynchronizeSingletonConstructor = LazySynchronizeSingleton.class.getDeclaredConstructor();
        lazySynchronizeSingletonConstructor.setAccessible(true);
        LazySynchronizeSingleton lazySynchronizeSingleton1 = lazySynchronizeSingletonConstructor.newInstance();
        System.out.println(lazySynchronizeSingleton == lazySynchronizeSingleton1);


        //双重锁检查
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getLazyDoubleCheckSingleton();
        Constructor<LazyDoubleCheckSingleton> lazyDoubleCheckSingletonConstructor = LazyDoubleCheckSingleton.class.getConstructor();
        lazyDoubleCheckSingletonConstructor.setAccessible(true);
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton1 = lazyDoubleCheckSingletonConstructor.newInstance();
        System.out.println(lazyDoubleCheckSingleton == lazyDoubleCheckSingleton1);


        //lock
        LazyLockSingleton lazyLockSingleton = LazyLockSingleton.getLazyLockSingleton();
        Constructor<LazyLockSingleton> lazyLockSingletonConstructor = LazyLockSingleton.class.getConstructor();
        lazyLockSingletonConstructor.setAccessible(true);
        LazyLockSingleton lazyLockSingleton1 = lazyLockSingletonConstructor.newInstance();
        System.out.println(lazyLockSingleton == lazyLockSingleton1);
    }
}
