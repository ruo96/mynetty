package com.wrh.thread.Singleton;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonHolder {
    private byte[] data = new byte[1024];

    private SingletonHolder(){}

    /**/
    private static class Holder{
        private static SingletonHolder instance = new SingletonHolder();

    }

    public static SingletonHolder getInstance(){
        return Holder.instance;
    }

}
