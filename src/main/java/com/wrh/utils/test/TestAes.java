package com.wrh.utils.test;

import com.wrh.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestAes
 * @Description TODO
 * @Date 2021/3/26 11:29
 */
@Slf4j
public class TestAes {
    @Test
    public void Test14() {
        String email = "Imae2Q4g/AV9t+wK7oLmLwA1Ds7E6i551UD28s/ugL4=";
        System.out.println("AESUtils.decrypt(email) = " + AESUtils.decrypt(email));

        String originMail = "jiangshan02@bilibili.com";
        System.out.println("AESUtils.encrypt(originMail) = " + AESUtils.encrypt(originMail));
    }
}
