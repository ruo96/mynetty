package com.wrh.thread.Singleton;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonLazyAndDoubleCheckAndVolatile {
    private byte[] data = new byte[1024];

    private volatile static SingletonLazyAndDoubleCheckAndVolatile instance = null;

    Connection connection;
    Socket socket;

    private SingletonLazyAndDoubleCheckAndVolatile(){
        this.connection = null;
        this.socket = new Socket();
    }

    public static SingletonLazyAndDoubleCheckAndVolatile getInstance(){
        /**/
        if(null == instance){
            /*这种方式,加了volatile之后, 满足了多种 要求,并且防止了重排序的发生*/
            synchronized (SingletonLazyAndDoubleCheckAndVolatile.class){
                if(null == instance){
                    instance = new SingletonLazyAndDoubleCheckAndVolatile();
                }
            }
        }
        return instance;
    }

}
