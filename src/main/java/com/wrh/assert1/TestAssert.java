package com.wrh.assert1;

import org.springframework.util.Assert;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:06 2018/11/29 0029
 * @Modified By:
 */
public class TestAssert {
    public static void main(String[] args) {
        String i = "123";
        i = null;
        Assert.notNull(i,"must not null");
    }
}
