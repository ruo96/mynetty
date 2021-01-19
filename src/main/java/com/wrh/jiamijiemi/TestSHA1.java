package com.wrh.jiamijiemi;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wuruohong
 * @Classname TestSHA1
 * @Description TODO
 * @Date 2021/1/11 19:54
 */
@Slf4j
public class TestSHA1 {

    String content = "这是一段文字";
    @Test
    public void Test14() {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            System.out.println(sha1.digest(content.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
