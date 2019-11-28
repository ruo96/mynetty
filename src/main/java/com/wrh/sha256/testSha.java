package com.wrh.sha256;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:31 2019/11/13 0013
 * @Modified By:
 */
@Slf4j
public class testSha {

    @Test
    public void test(){
        String data = Digest.sha256("123");
        log.info("===> sha256:{}",data);  //a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3
    }
}
