package com.wrh.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:23 2019/11/21 0021
 * @Modified By:
 */
@Slf4j
public class TestIntern {

    @Test
    public void test1(){
        String str1 = new String("123");

        String str2 = str1.intern();

        log.info("str1 == str2 :{} ",str1 == str2);
    }
}
