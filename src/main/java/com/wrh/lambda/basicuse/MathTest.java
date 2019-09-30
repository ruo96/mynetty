package com.wrh.lambda.basicuse;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:35 2019/9/27 0027
 * @Modified By:
 */
@Slf4j
public class MathTest {

    /**
     * 计算和值
     */
    @Test
    public void test(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);

        int a = numList.stream().filter(e->e>2).mapToInt(Integer::intValue).sum();

        log.info("sum is : {}",a);
    }
}
