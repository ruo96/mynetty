package com.wrh.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestPeople
 * @Description TODO
 * @Date 2020/9/2 16:14
 */
@Slf4j
public class TestPeople {
    @Test
    public void Test14() {
        People p  = People.builder().age(1).name("w1").build();
        System.out.println(p);

    }
}
