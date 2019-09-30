package com.wrh.designMode.singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:14 2019/9/30 0030
 * @Modified By:
 */
public class LazyLockSingleton {

    private static LazyLockSingleton lazyLockSingleton = null;

    /**
     * 锁
     **/
    private static Lock lock = new ReentrantLock();

    /**
     * 构造函数私有化
     * */
    private LazyLockSingleton() {
    }

    public static LazyLockSingleton getLazyLockSingleton() {
        try {
            lock.lock();
            if (lazyLockSingleton == null) {
                lazyLockSingleton = new LazyLockSingleton();
            }
        } finally {
            lock.unlock();
        }

        return lazyLockSingleton;
    }
}
