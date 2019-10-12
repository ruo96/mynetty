package com.wrh.math;

import org.elasticsearch.search.aggregations.metrics.ParsedSingleValueNumericMetricsAggregation;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:58 2019/6/11 0011
 * @Modified By:
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = 123;
        Integer i2 = 123;
        System.out.println(i1 == i2);

        Integer i3 = 127;
        Integer i4 = 127;
        System.out.println(i3 == i4);

        Double i5 = 1.0;
        Double i6 = 1.0;
        System.out.println(i5 == i6);

        Integer i7 = 40;
        Integer i8 = new Integer(40);
        System.out.println(i7 == i8);



    }

    @Test
    public void test(){
        int i = 1;
        System.out.println(i+++i++);
        System.out.println(i);

        int j = 1;
        System.out.println(j++ + ++j);

    }


    @Test
    public void test1(){
        int i = 1;
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);


        int j = 1;
        System.out.println(++j);
        System.out.println(++j);
        System.out.println(++j);
        System.out.println(++j);

        Integer i1 = 250;
        Integer i2 = 250;
        System.out.println(i1 == i2);

        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println(i3 == i4);


    }

    @Test
    public void test2(){
        int i = getResult();
        System.out.println(i);

        Integer j = getResult();
        System.out.println(j);


    }

    private int getResult() {
        try {
            Thread.sleep(1);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return 2;
        }
    }

}
