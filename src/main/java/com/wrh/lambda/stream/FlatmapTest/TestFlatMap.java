package com.wrh.lambda.stream.FlatmapTest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:41 2019/10/21 0021
 * @Modified By:
 */
@Slf4j
public class TestFlatMap {
    @Test
    public void test(){
        List<String> list = Arrays.asList("hello","world");
        List<String> list1 = list.stream().map(s->s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        log.info("===> 获取list： {}", JSON.toJSONString(list1));

        list1 = list.stream().limit(1).collect(Collectors.toList());
        log.info("===> 获取list： {}", JSON.toJSONString(list1));


    }
}
