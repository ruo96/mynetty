package com.wrh.TimeCalculate;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    @Test
    public void test2(){

        log.info("===>{}", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        log.info("===>{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    @Test
    public void test3(){
        for (int i = 0; i < 100; i++) {

            log.info(UUID.randomUUID().toString().substring(0,6));
        }
    }

    @Test
    public void test4(){
        CqTraceUpload cqTraceUpload = new CqTraceUpload();
        cqTraceUpload.setBIZ_TYPE("123");
        LocalDateTime dateTime = LocalDateTime.now();
        cqTraceUpload.setREQ_TIME(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        cqTraceUpload.setREQ_ID(dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + UUID.randomUUID().toString().substring(0,6));
        cqTraceUpload.setAUTH_ID("");
        cqTraceUpload.setPARAM("");

        log.info("===>{}", JSON.toJSONString(cqTraceUpload));
    }
}
