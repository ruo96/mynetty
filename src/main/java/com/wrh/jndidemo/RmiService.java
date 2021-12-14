package com.wrh.jndidemo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wuruohong
 * @Classname RmiService
 * @Description 定义接口
 * @Date 2021/12/14 10:50
 */
public interface RmiService extends Remote {
    String sayHello() throws RemoteException;
}
