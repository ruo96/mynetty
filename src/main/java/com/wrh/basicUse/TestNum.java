package com.wrh.basicUse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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

    @Test
    public void Test63() {
       int i = 10;
       String a = String.format("%03d", i);
        System.out.println("a = " + a);

        Long a1 = null;
        long a2 = a1;
        System.out.println("a2 = " + a2);

    }

    @Test
    public void Test75() {
        Long a = 100L;
        Long b = a;
        a = 200L;
        System.out.println("b = " + b);

    }

    @Test
    public void Test84() {
        long begin = 1;
        long end = 1048575;

        /*long begin = 1;
        long end = 1000000;*/

        long totalQueryCnt = end - begin + 1;
        long ckBegin = begin;
        long ckEnd = end;

        long querySize = 500000;


            for (long i = 0; i <= totalQueryCnt / querySize; i++) {
                begin = ckBegin + i * querySize;
                end = ckBegin + (i + 1) * querySize - 1;
                end = end > ckEnd ? ckEnd : end;

                System.out.println("begin = " + begin);
                System.out.println("end = " + end);

                if (end > ckEnd) {
                    log.info("[split]>>> add task end", i+1);
                    break;
                }

                System.out.println("between  " +(begin - 1)+ "  limit " + (end - begin + 1));
            }


    }
    
    @Test
    public void Test121() {
        float v = RandomUtils.nextFloat(1,10000);
        String s = String.format("%.2f",v);
        System.out.println("v = " + v);
        System.out.println("s = " + s);
    }
}
