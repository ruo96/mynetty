package com.wrh.math;

import com.wrh.elasticsearch.Student;
import com.wrh.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Test
    public void Test42() {
        System.out.println(MathUtils.getPercentString(100L,500L,4));

    }

    @Test
    public void Test49() {
        Integer i = 100;
        System.out.println(i.toString());

    }

    @Test
    public void Test56() {
        Long a = 4L;
        Long b = 4L;
        long c = a.longValue();
        System.out.println(a.longValue());
        System.out.println(a*b);
        System.out.println(c);

        Long d = null;
        if(Objects.isNull(d)){
            System.out.println("d is null");
        }
    }

    @Test
    public void Test72() {
        Integer i = new Integer(0);
        if(i.equals(0)){
            System.out.println("equal");
        }else {
            System.out.println(i);
        }


    }

    @Test
    public void Test85() {
        BigDecimal b  =new BigDecimal("0.000044456");
        String show;
        BigDecimal percentValue =b.multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP);
//        BigDecimal percentValue =b.multiply(new BigDecimal("100")).setScale(2);
        double a = percentValue.doubleValue();
        System.out.println("a is : " + a);
        /*if(b.compareTo(BigDecimal.ZERO) == 1){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }*/

        if(a > 0){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }
        System.out.println(show);

    }

    public static String getPercentString(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }

    public static String getPercentString0(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }

    @Test
    public void Test114() {
        System.out.println(getPercentString(100000,1,4));
        System.out.println(getPercentString0(100000,3,4));

    }

    public static final Integer BIGDECIMAL_LIMIT = 100;

    public static String getPercentString1(long numerator ,long denominator , int fractionDigit){
        int scale = 2;
        if(numerator / denominator >= BIGDECIMAL_LIMIT){
            scale = 0;
        }
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(scale,BigDecimal.ROUND_HALF_UP);
        double a = percentValue.doubleValue();
        String show;
        if(a > 0){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }
        return show;
    }

    public static String getPercentStringWithoutSign(long numerator ,long denominator , int fractionDigit){
        int scale = 2;
        if(numerator / denominator >= BIGDECIMAL_LIMIT){
            scale = 0;
        }
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(scale,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }
    @Test
    public void Test155() {
        System.out.println(getPercentString1(99, 1 , 4));
        System.out.println(getPercentStringWithoutSign(99, 1 , 4));

    }


}
