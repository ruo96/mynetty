package com.wrh.TimeCalculate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:49 2019/9/30 0030
 * @Modified By:
 */
@Slf4j
public class TestTime {

    @Test
    public void test(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(formatter));

        //10分钟前
        String d1 = now.minusMinutes(10).format(formatter);
        //10分钟后
        String d2 = now.plusMinutes(10).format(formatter);

        System.out.println(d1);
        System.out.println(d2);


        LocalDateTime t5 = LocalDateTime.parse("2019-01-01 00:00:00", formatter);

        System.out.println(t5.format(formatter));
    }

    @Test
    public void test1(){
        log.info("=== timestamp: {}",System.currentTimeMillis());
    }
}
