package com.wrh.designMode.singleton.EnumTest;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:40 2019/11/8 0008
 * @Modified By:
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        SingleInstance.INSTANCE.fun1();
    }

}
