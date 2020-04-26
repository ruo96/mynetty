package com.wrh.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:09 2019/10/21 0021
 * @Modified By:
 */
@Slf4j
public class TestBigDecimal {

    @Test
    public void test(){
        BigDecimal bigDecimal = BigDecimal.valueOf(0.11D);
    }

    @Test
    public void Test() {
        BigDecimal b1 = new BigDecimal("0.99");
        BigDecimal b2 = b1.multiply(BigDecimal.TEN);
        BigDecimal b3 = b1.multiply(new BigDecimal("10"));
        BigDecimal b4 = b1.multiply(BigDecimal.valueOf(10.00f));
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        System.out.println(b4.toString());
    }

    @Test
    public void Test1() {
        double v = 9.9f;
        System.out.println(v);
        double v2 = Double.valueOf(9.9f);
        System.out.println(v2);
    }
}
