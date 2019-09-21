package com.wrh.lambda.flatmapuse;

import com.alibaba.fastjson.JSON;
import com.wrh.lambda.basicuse.PersonModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:27 2019/9/19 0019
 * @Modified By:
 */
@Slf4j
public class TestFlatMap {
    @Test
    public void test(){
        List<PersonModel> data = Data.getData();

        List<String> collect = data.stream()
                .flatMap(person-> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());

        List<Stream<String>> collect1 = data.stream()
                .map(person -> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());

        List<String> collect2 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(Arrays::stream).collect(Collectors.toList());

        List<String> collect3 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(str -> Arrays.asList(str).stream()).collect(Collectors.toList());

        log.info("=== collect is :{}", JSON.toJSONString(collect));
        log.info("=== collect1 is :{}", collect1.get(0).findFirst().get());
        log.info("=== collect1 is :{}", collect1.get(1).findFirst().get());
        log.info("=== collect1 is :{}", collect1.get(2).findFirst().get());
        log.info("=== collect1 is :{}", collect1.get(3).findFirst().get());
        log.info("=== collect2 is :{}", JSON.toJSONString(collect2));
        log.info("=== collect3 is :{}", JSON.toJSONString(collect3));

    }
}
