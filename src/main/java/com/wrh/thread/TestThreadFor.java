package com.wrh.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:24 2018/11/8 0008
 * @Modified By:
 */
@Slf4j
public class TestThreadFor {
    public static void main(String[] args) throws InterruptedException {

//        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        Map<String,String> map = new LinkedHashMap<>();

        Thread t1 = new Thread(() -> {
            for(int i =0;i<100;i++){
                map.put("T1-"+i,"t1data-"+i);
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i =0;i<100;i++){
                map.put("T2-"+i,"t2data-"+i);
            }
        });
        t2.start();
        t1.start();

        Thread.sleep(10000);

        log.info("map的大小:{}",map.size());
        log.info("map:[{}]",map);


    }
}
