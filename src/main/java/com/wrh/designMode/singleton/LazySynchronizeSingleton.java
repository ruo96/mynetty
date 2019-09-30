package com.wrh.designMode.singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:14 2019/9/30 0030
 * @Modified By:
 */
public class LazySynchronizeSingleton {

    private static LazySynchronizeSingleton lazySynchronizeSingleton= null;

    /**
     * 构造函数私有化
     * */
    private LazySynchronizeSingleton() {
    }

    public synchronized static LazySynchronizeSingleton getLazySynchronizeSingleton() {
        if (lazySynchronizeSingleton == null) {
            lazySynchronizeSingleton = new LazySynchronizeSingleton();
        }

        return lazySynchronizeSingleton;
    }
}
