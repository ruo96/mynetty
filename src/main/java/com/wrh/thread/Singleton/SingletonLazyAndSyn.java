package com.wrh.thread.Singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonLazyAndSyn {
    private byte[] data = new byte[1024];

    private static SingletonLazyAndSyn instance = null;

    private SingletonLazyAndSyn(){}

    public static synchronized SingletonLazyAndSyn getInstance(){
        /*这种方式虽然能够满足懒加载又可以保证实例的唯一性, 但是synchronized天生的排他性, 导致了这个方法只能同一时刻被一个线程访问, 性能底下*/
        if(null == instance){
            instance = new SingletonLazyAndSyn();
        }
        return instance;
    }

}
