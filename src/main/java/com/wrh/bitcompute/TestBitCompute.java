package com.wrh.bitcompute;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:27 2019/8/31 0031
 * @Modified By:
 */
@Slf4j
public class TestBitCompute {

    @Test
    public void test(){
        int i = 16;
        int j = i >>>1;
        int k = i >>>2;
        int m = i >>>3;
        log.info("=== result j is :{}",j);
        log.info("=== result k is :{}",k);
        log.info("=== result m is :{}",m);
    }


}
