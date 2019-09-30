package com.wrh.lambda.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:09 2019/9/27 0027
 * @Modified By:
 */
@Slf4j
public class CreatStream {

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        // 从集合创建
        Stream<String> stream = list.stream();
        Stream<String> stream1 = list.parallelStream();

        // 从数组创建
        IntStream stream2 = Arrays.stream(new int[]{2, 3, 5});

        // 创建数字流
        IntStream intStream = IntStream.of(1, 2, 3);

        // 使用random创建
        IntStream limit = new Random().ints().limit(10);

        limit.forEach(e->{
            log.info("num is : {}",e);
        });

    }

    @Test
    public void test1(){
        String str = "my name is 007";

        Stream.of(str.split(" ")).filter(s -> s.length() > 2)
                .map(s -> s.length()).forEach(System.out::println);

        Stream<String> split = Stream.of(str.split(" "));
        split.forEach(e->{
            log.info("===> after : {}",e);
        });

    }

    @Test
    public void test2(){
        String str = "my name is 007";
        Stream.of(str.split(" ")).peek(System.out::println); // 不会有信息打印

        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

    }

    @Test
    public void test3(){
        BeanCopy1 copy1 = new BeanCopy1();
//        copy1.setCompanyId(1);
        copy1.setCompanyId(null);
        copy1.setName("wrh");

        BeanCopy2 copy2 = new BeanCopy2();
        BeanUtils.copyProperties(copy1,copy2);

        log.info("{}",copy2);

    }
}
