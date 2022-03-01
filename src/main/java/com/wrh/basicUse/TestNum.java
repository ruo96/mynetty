package com.wrh.basicUse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void Test63() {
        Long a = 100L;
        double rate = 0.475;
        Long b = Double.valueOf(a * rate).longValue();
        System.out.println("b = " + b);

    }

    @Test
    public void Test72() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        StringBuffer sb = new StringBuffer();
        for (Integer p : list) {
            sb.append(p).append(",");
        }
        System.out.println("sb.toString() = " + sb.toString());

    }

    @Test
    public void Test89() {
        int a = 20;
        int b = a>>5;
        int c = a>>>5;
        System.out.println("b = " + b);
        System.out.println("c = " + c);

    }

    @Test
    public void Test99() {

        try {
            int i = 10/0;
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }


    }
}
