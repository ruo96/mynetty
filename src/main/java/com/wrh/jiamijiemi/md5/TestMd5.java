package com.wrh.jiamijiemi.md5;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:01 2019/8/27 0027
 * @Modified By:
 */
@Slf4j
public class TestMd5 {
    @Test
    public void testMd5(){

        String p1 = "CodeNZ002";
        String p2 = "CodeNZ0021";

        getMd5(p1);
        getMd5(p2);


    }

    private void getMd5(String p1){
        Map<String, Object> params = new HashMap<>();
        params.put("password" , p1);
        Digest.MD5((String) params.get("password")).ifPresent(i -> params.put("password", i));

        log.info("密码为:{}",p1);
        log.info("===> 模拟更改密码后的数据库存放md5：{}",params.get("password"));

        String passwordMd5 = EncryptUtils.MD5(p1);
        log.info("===> 模拟登录时候的md5:{}", passwordMd5);
        log.info("==========================================");
    }

    @Test
    public void Test43() {
        /** 加盐 用户密码*/
        String s = "abc123456";
        System.out.println("DigestUtils.md5DigestAsHex(s) = " + DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8)));
        System.out.println("MD5.create().digestHex(s) = " + MD5.create().digestHex(s));

        String hashpw = BCrypt.hashpw(s, BCrypt.gensalt(10));
        System.out.println("hashpw = " + hashpw);

        String salt = BCrypt.gensalt(12);
        System.out.println("salt = " + salt);

        String a = "123";
        String b = "456";
        String ap = BCrypt.hashpw(a, salt);
        String bp = BCrypt.hashpw(b, salt);
        System.out.println("ap = " + ap);
        System.out.println("bp = " + bp);

        System.out.println("BCrypt.checkpw(a, ap) = " + BCrypt.checkpw(a, ap));
        System.out.println("BCrypt.checkpw(b, bp) = " + BCrypt.checkpw(b, bp));

    }
}
