package com.wrh.encode;

import jodd.util.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestEncode
 * @Description TODO
 * @Date 2020/12/16 17:37
 */
@Slf4j
public class TestEncode {
    
    @Test
    public void Test15() {
//        BCrypt b = new BCrypt(0xa);
        String password = "123456";
        long md5Begin = System.currentTimeMillis();
        DigestUtils.md5Hex(password);
        long md5End = System.currentTimeMillis();
        System.out.println("md5 time:"+(md5End - md5Begin));
        long bcrytBegin = System.currentTimeMillis();
        BCrypt.hashpw(password, BCrypt.gensalt(10));
        long bcrytEnd = System.currentTimeMillis();
        System.out.println("bcrypt Time:" + (bcrytEnd- bcrytBegin));
        
    }
}
