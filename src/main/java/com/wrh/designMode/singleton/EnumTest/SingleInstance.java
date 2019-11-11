package com.wrh.designMode.singleton.EnumTest;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:36 2019/11/8 0008
 * @Modified By:
 */
public enum SingleInstance {

        INSTANCE;
        public void fun1() {
            // do something
            System.out.println("current id: " + Thread.currentThread().getId()  + " instance: " + INSTANCE);
        }


    // 使用
    //SingleInstance.INSTANCE.fun1();
}
