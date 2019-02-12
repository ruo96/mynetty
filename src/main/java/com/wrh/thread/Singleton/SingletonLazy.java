package com.wrh.thread.Singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonLazy {
    private byte[] data = new byte[1024];

    private static SingletonLazy instance = null;

    private SingletonLazy(){}

    public static SingletonLazy getInstance(){
        /*此处如果涉及多线程, 会出现多次初始化的情况, 因此这种方法是有缺陷的*/
        if(null == instance){
            instance = new SingletonLazy();
        }
        return instance;
    }

}
