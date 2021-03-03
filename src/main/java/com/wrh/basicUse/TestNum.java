package com.wrh.basicUse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author wuruohong
 * @Classname TestNum
 * @Description TODO
 * @Date 2021/1/18 11:00
 */
@Slf4j
public class TestNum {
    @Test
    public void Test14() {
       int a = 0b010;
        System.out.println(a);
    }

    @Test
    public void Test21() {
        long activeAccCnt = 143;
        String percentStr = "25%";
        String percent = percentStr.replace("%","");
        BigDecimal percentBig = new BigDecimal(percent).divide(new BigDecimal("100"));

        long value = percentBig.multiply(new BigDecimal(activeAccCnt)).setScale(0,BigDecimal.ROUND_FLOOR).longValue();
        String result = String.valueOf(value);

        System.out.println(result);
    }
}
