package com.wrh.algorithum.hex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:16 2019/11/15 0015
 * @Modified By:
 */
@Slf4j
public class testHex {
    @Test
    public void test1() throws UnsupportedEncodingException {
        byte[] b = HexUtils.StrToBCDBytes("1234");
        log.info("b:{}",b);
        String a = HexUtils.bcdToString(b);
        log.info("===>{}",a);
    }
}
