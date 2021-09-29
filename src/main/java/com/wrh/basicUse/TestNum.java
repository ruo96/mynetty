package com.wrh.basicUse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

    @Test
    public void Test36() {
        DecimalFormat df = new DecimalFormat("0.##"); // ##表示2位小数
//        DecimalFormat df = new DecimalFormat("0");
        Double b = 715944894.13;
        System.out.println("removeScientificNotation(b) = " + removeScientificNotation(b));
        System.out.println("no removeScientificNotation(b) = " + b);

    }

    public static String removeScientificNotation(Double num) {
        DecimalFormat df = new DecimalFormat("0.##"); // ##表示2位小数
//        DecimalFormat df = new DecimalFormat("0");
        return df.format(num);
    }

    @Test
    public void Test53() {
        for (int i = 0; i < 10; i++) {

            System.out.println("RandomUtils.nextLong() = " + RandomUtils.nextLong(100, 1000));
        }

    }
}
