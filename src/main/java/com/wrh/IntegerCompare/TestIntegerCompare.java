package com.wrh.IntegerCompare;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:41 2019/9/9 0009
 * @Modified By:
 */
@Slf4j
public class TestIntegerCompare {
    @Test
    public void test(){
        Integer i = 127;

        int j = 127;

        log.info("{}",i.equals(j));

        Integer i1 = 256;

        int j1 = 256;

        log.info("{}",i1.equals(j1));
        log.info("{}",i1 == j1);
        log.info("{}",i == j);



    }
}
