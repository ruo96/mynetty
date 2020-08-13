package com.wrh.lambda.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wuruohong
 * @Classname TestBasicUse
 * @Description TODO
 * @Date 2020/6/24 19:26
 */
@Slf4j
public class TestBasicUse {

    @Test
    public void Test15() {
        Predicate<Integer> predicate = x -> x > 10;
        System.out.println(predicate.test(18));
        System.out.println(predicate.test(8));

        Consumer<String> consumer = System.out::println;
        consumer.accept("hello");

        Function<String, Integer> function = String::length;
        System.out.println(function.apply("wrh"));

        Supplier<Integer> supplier = ()-> RandomUtils.nextInt(0,10);
        System.out.println(supplier.get());

        UnaryOperator<Boolean> unaryOperator = u-> !u;
        System.out.println(unaryOperator.apply(true));

        BinaryOperator<Integer> binaryOperator = (x, y) -> x * y;
        System.out.println(binaryOperator.apply(2,3));
    }

    @Test
    public void Test41() {
        List<String> list = Stream.of("w1","w2","w3").collect(Collectors.toList());
        System.out.println(list);

    }
}
