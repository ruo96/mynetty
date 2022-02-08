package com.wrh.utils.test;

import com.wrh.utils.OkHttpUtils;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestOKHttp
 * @Description TODO
 * @Date 2022/1/21 10:35
 */
public class TestOKHttp {
    @Test
    public void Test348() {
        String result = OkHttpUtils.builder().url("http://www.baidu.com")
                // 有参数的话添加参数，可多个
                .addParam("w1", "w1")
                .addParam("w2", "w2")
                // 也可以添加多个
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .get()
                .sync();
        System.out.println("result = " + result);
    }
}
