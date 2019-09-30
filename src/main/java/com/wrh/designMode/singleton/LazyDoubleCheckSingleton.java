package com.wrh.designMode.singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:13 2019/9/30 0030
 * @Modified By:
 */
public class LazyDoubleCheckSingleton {

    /**
     * 使用volatile进行修饰，禁止指令重排
     * */
    private static volatile LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    /**
     * 构造函数私有化
     * */
    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getLazyDoubleCheckSingleton() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }

        return lazyDoubleCheckSingleton;
    }
}
