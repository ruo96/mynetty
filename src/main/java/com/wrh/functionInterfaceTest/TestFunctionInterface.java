package com.wrh.functionInterfaceTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 11:38 2019/10/15 0015
 * @Modified By:
 */
@Slf4j
public class TestFunctionInterface {

    @Test
    public void test(){
        Predicate<Integer> predicate = x -> x > 185;
        Student student = new Student("9龙", 23, 175);
        System.out.println(
                "9龙的身高高于185吗？：" + predicate.test(student.getStature()));

        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);

        Supplier<Integer> supplier =
                () -> Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);

        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);

//        test();est(() -> "我是一个演示的函数式接口");
    }


}
