package com.wrh.stream.minmax;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:57 2018/8/29 0029
 * @Modified By:
 */
@Slf4j
public class TestStreamMinMax {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        /**
         * 测试缩减
         */
        /*Optional<Integer> integer1 = list.stream().min(Integer::compareTo);
        if(integer1.isPresent()){
            log.info("min of this list is : {}",integer1.get());
        }

        Optional<Integer> integer2 = list.stream().max(Integer::compareTo);
        if(integer2.isPresent()){
            log.info("max of this list is : {}",integer2.get());
        }*/

        /**
         * 测试reduce方法
         */
        /*Optional<Integer> integer3 = list.stream().reduce((a,b)-> a*b);
        if(integer3.isPresent()){
            log.info("reduce of this list is : {}",integer3.get());
        }*/

        Integer integer3 = list.parallelStream().reduce(2,(a,b)-> a *(b * 2), (a,b) -> a * b);
        log.info("reduce of this list is : {}",integer3);


        /*list.forEach(e->{
            log.info(" list is : {}",e);
        });


        list.sort(Integer::compareTo);
        log.info("sort list is: {}",list);*/

    }
}
