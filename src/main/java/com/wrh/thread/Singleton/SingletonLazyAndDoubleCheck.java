package com.wrh.thread.Singleton;

import java.net.Socket;
import java.sql.Connection;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/11 0011
 * @Modified By:
 */
public final class SingletonLazyAndDoubleCheck {
    private byte[] data = new byte[1024];

    private static SingletonLazyAndDoubleCheck instance = null;

    Connection connection;
    Socket socket;

    private SingletonLazyAndDoubleCheck(){
        this.connection = null;
        this.socket = new Socket();
    }

    public static SingletonLazyAndDoubleCheck getInstance(){
        /**/
        if(null == instance){
            /*这种方式,看起来虽然完美,但是容易产生空指针异常, 因为connection和socket还没有初始化过*/
            synchronized (SingletonLazyAndDoubleCheck.class){
                if(null == instance){
                    instance = new SingletonLazyAndDoubleCheck();
                }
            }
        }
        return instance;
    }

}
