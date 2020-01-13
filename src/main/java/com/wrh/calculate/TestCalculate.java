package com.wrh.calculate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:58 2019/1/9 0009
 * @Modified By:
 */
@Slf4j
public class TestCalculate {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    @Test
    public void Test() {
        String a = "123";
        Long b = Long.valueOf(a);
        log.info("b:{}",b);
    }
}
