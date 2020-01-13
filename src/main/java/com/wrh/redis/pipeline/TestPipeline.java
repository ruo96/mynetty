package com.wrh.redis.pipeline;

import com.wrh.redis.RedisTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Classname TestPipeline
 * @Description TODO
 * @Date 2020/1/13 11:50
 * @Created by wuruohong
 */
@Slf4j
@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@SpringBootTest
public class TestPipeline {

    @Autowired
    private RedisTools redisTools;

    @Test
    public void Test1() {
        redisTools.set("wrh","123");
    }

    @Test
    public void Test2() {
        redisTools.set("expire1", "dead", 10, TimeUnit.SECONDS);
    }

    /**
     * 测试pipeline  批量插入
     */
    @Test
    public void Test3() {
        List<Map<String, String>> saveList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("key","w1");
        map.put("value","r1");

        Map<String,String> map1 = new HashMap<>();
        map1.put("key","w2");
        map1.put("value","r2");

        saveList.add(map);
        saveList.add(map1);
        redisTools.pipelineBatchInsert(saveList,TimeUnit.SECONDS,20);
    }

    /**
     * 测试多种不同的操作 pipeline
     */
    @Test
    public void Test4() {
        List<Map<String, String>> saveList = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("key","w1");
        map.put("value","r1");

        Map<String,String> map1 = new HashMap<>();
        map1.put("key","w2");
        map1.put("value","r2");
        saveList.add(map);
        saveList.add(map1);


        List<Map<String, String>> countList = new ArrayList<>();
        Map<String,String> map2 = new HashMap<>();
        map2.put("key","c1");

        Map<String,String> map3 = new HashMap<>();
        map3.put("key","c2");
        countList.add(map2);
        countList.add(map3);

        Map<String, List<Map<String, String>>> reqMap = new HashMap<>();
        reqMap.put("INSERT", saveList);
        reqMap.put("INCR", countList);

        redisTools.pipelineMultiOpr(reqMap, TimeUnit.SECONDS, 20);

    }
}
