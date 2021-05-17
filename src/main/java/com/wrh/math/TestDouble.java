package com.wrh.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.NumberFormat;

/**
 * @author wuruohong
 * @Classname TestDouble
 * @Description TODO
 * @Date 2020/5/27 16:43
 */
@Slf4j
public class TestDouble {

    @Test
    public void Test() {
        System.out.println(getPercent(1.1,2.2,30));
    }
    public static String getPercent(double numerator ,double denominator , int fractionDigit){
        if(denominator == 0) {
            return null;
        }
        double num = numerator  / denominator;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(fractionDigit);
        numberFormat.setMaximumFractionDigits(fractionDigit);
        return numberFormat.format(num);
    }

    @Test
    public void Test1() {
        Double a = 1.234;
        Long b = a.longValue();
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void Test2() {
        Integer a = 33;
        changeInteger(a);
        System.out.println(a);
    }

    private void changeInteger(Integer a ) {
        a = 9999;
    }
    @Test
    public void Test51() {
        double rate = 1.0;
        System.out.println(0.145 * rate);

    }

    @Test
    public void Test58() {
        Double a = 0.2354;
        Double b = (1-a)*100;
        System.out.println(b);

    }

    @Test
    public void Test66() {
        String a = "2.23";
        Double b = Double.valueOf(a);
        System.out.println(b);

    }

    @Test
    public void Test74() {
        double a = 0.01;
        if (a > 0) {
            System.out.println(" a is big");
        } else {
            System.out.println("a is not big");
        }

    }
    @Test
    public void Test84() {
        final Boolean a;
        /*if (!a) {
            a = true;
        }
*/
    }
}
