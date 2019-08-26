package com.wrh.lambda.basicuse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:51 2019/6/4 0004
 * @Modified By:
 */
public class TestFlagMap {
    public static void main(String[] args) {
        List<PersonModel> data = Data.getData();

        List<String> collect = data.stream()
                .flatMap(person-> Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());

        System.out.println("collect :  " + collect);

        List<Stream<String>> collect1 = data.stream()
                .map(person->Arrays.stream(person.getName().split(" "))).collect(Collectors.toList());

        System.out.println("collect1 :  " + collect1);

        List<String> collect2 = data.stream()
                .map(person->person.getName().split(" "))
                .flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println("collect2 :  " + collect2);

        List<String> collect3 = data.stream()
                .map(person->person.getName().split(" "))
                .flatMap(str->Arrays.asList(str).stream()).collect(Collectors.toList());
        System.out.println("collect3 :  " + collect3);




    }
}
