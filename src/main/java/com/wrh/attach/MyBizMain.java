package com.wrh.attach;

/**
 * 模拟业务方法
 * @author xujian
 * 2021-03-12 10:52
 **/
public class MyBizMain {
    public String foo() {
        return "------我是MyBizMain-----";
    }

    public static void main(String[] args) throws InterruptedException {
        MyBizMain myBizMain = new MyBizMain();
        while (true) {
            System.out.println(myBizMain.foo());
            Thread.sleep(3000);
        }
    }
}
