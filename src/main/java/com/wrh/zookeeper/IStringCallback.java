package com.wrh.zookeeper;

import org.apache.zookeeper.AsyncCallback;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:57 2019/2/26 0026
 * @Modified By:
 */
public class IStringCallback implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("创建结果: " + i);
        System.out.println("创建路径: " + s);
        System.out.println("创建真实路径: " + s1);
    }
}
