package com.wrh.math;

import org.elasticsearch.search.aggregations.metrics.ParsedSingleValueNumericMetricsAggregation;

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
}
