package com.wrh.redis.DistrubutedLock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:38 2019/12/9 0009
 * @Modified By:
 */
@Slf4j
public class TestD {
    @Test
    public void test(){
        BasePrefix basePrefix = new BasePrefix("prefix");
        String a = basePrefix.getPrefix();
        log.info(":===> {}",a);
    }
}
