package com.wrh.lambda.stream.reduce;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wuruohong
 * @Classname TestReduce
 * @Description TODO
 * @Date 2021/2/5 14:17
 */
@Slf4j
public class TestReduce {

    @Test
    public void Test15() {
        Stream<String> stream = Stream.of("hello","this","world","goodbye");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        System.out.println("longest.get() = " + longest.get());
    }

    @Test
    public void Test27() {
        Stream<String> stream = Stream.of("hello","this","world","goodbye");
        Integer longest = stream.reduce(0, (sum, s2) -> sum + s2.length(), (a, b)->a+b);
        System.out.println("longest = " + longest);

       /* int sum = stream.mapToInt(e -> e.length()).sum();
        System.out.println("sum = " + sum);*/
    }

    /**
     * Function是一个接口，那么Function.identity()是什么意思呢？这要从两方面解释：
     * Java 8允许在接口中加入具体方法。接口中的具体方法有两种，default方法和static方法，identity()就是Function接口的一个静态方法。
     * Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式。
     */
    @Test
    public void Test36() {
        Stream<String> stream = Stream.of("hello","this","world","goodbye");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("map = " + map);

    }

    @Test
    public void Test52() {
        Stream<String> stream = Stream.of("hello","this","world","goodbye");
        HashSet<String> hashset = stream.collect(Collectors.toCollection(HashSet::new));
        System.out.println("hashset = " + hashset);
    }

    @Test
    public void Test60() {
        Stream<String> stream = Stream.of("hello","this","world","goodbye");
        String joined1 = stream.collect(Collectors.joining());
        stream = Stream.of("hello","this","world","goodbye");
        String joined2 = stream.collect(Collectors.joining(","));
        stream = Stream.of("hello","this","world","goodbye");
        String joined3 = stream.collect(Collectors.joining(",","{","}"));
        System.out.println("joined1 = " + joined1);
        System.out.println("joined2 = " + joined2);
        System.out.println("joined3 = " + joined3);

    }
}
