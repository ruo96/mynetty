package com.wrh.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2019/9/4 0004
 * @Modified By:
 */
@Slf4j
public class TestGetLoader {
    @Test
    public void test(){
        log.info(System.getProperty("java.class.path"));
    }
}
