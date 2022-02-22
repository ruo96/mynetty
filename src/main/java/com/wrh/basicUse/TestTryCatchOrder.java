package com.wrh.basicUse;

import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestTryCatchOrder
 * @Description TODO
 * @Date 2022/2/8 14:31
 */
public class TestTryCatchOrder {

    @Test
    public void Test12() {
        System.out.println(getValueFromCache());
    }

    public int getValueFromCache() {
        int x = 1;
        try {
            int i = 1/0;
            return x;
        } catch(Exception e) {
            x = 3;
            return x;
        } finally {
            x = 2;
            return x;
        }
    }
}
