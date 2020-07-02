package com.wrh.enums;

/**
 * @author wuruohong
 * @Classname EnumSingleton
 * @Description 枚举单例
 * @Date 2020/6/22 16:28
 */
public class EnumSingleton {
    private EnumSingleton() {
    }

    private enum Singleton {
        INSTANCE;

        private final EnumSingleton instance;

        Singleton() {
            instance = new EnumSingleton();
        }

        private EnumSingleton getInstance() {
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
