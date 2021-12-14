package com.wrh.jndidemo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wuruohong
 * @Classname MyRmiServiceImpl
 * @Description TODO
 * @Date 2021/12/14 10:51
 */
public class MyRmiServiceImpl extends UnicastRemoteObject implements RmiService {

    protected MyRmiServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "hello world!";
    }
}
