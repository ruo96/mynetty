package com.wrh.lambda.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    /**
     * 创建流的不同方式
     */
    @Test
    public void test4() throws IOException {
       //第一种  通过集合来生成
        List<Integer> interList = Arrays.asList(1,2,3,4,5);
        Stream<Integer> stream = interList.stream();

        //第二种 通过数组来生成
        //这种方法生成的流是数值流，而不是上面的那种，补充一点是，数值流可以避免计算过程中的拆箱装箱，提高性能
        int[] intArr = new int[]{1,2,3,4,5};
        IntStream stream1 = Arrays.stream(intArr);

        //第三种， 通过值来生成
        Stream<Integer> stream2 = Stream.of(1,2,3,4,5);
        //还可以产生一个空流
        Stream<Integer> stream3 = Stream.empty();

        //第四种， 通过文件生成
        Stream<String> liners = Files.lines(Paths.get("e:\\data\\stream.txt"));
        liners.forEach(e->{
            log.info("{}",e);
        });

    }

    @Test
    public void test5() throws IOException {
        //第五种，通过函数生成

        Stream<Integer> stream = Stream.iterate(0,n->n+2).limit(5);
        stream.forEach(System.out::print);

        Stream<Double> stream1 = Stream.generate(Math::random).limit(5);
        stream1.forEach(System.out::print);

    }
}
