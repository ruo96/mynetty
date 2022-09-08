package com.wrh.jiamijiemi.bcrypt;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.Test;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/8/31 15:16
 */
public class TestBcrypt {

    @Test
    public void Test11() {
        BCrypt bCrypt = new BCrypt();
        String gensalt = BCrypt.gensalt(); // 29 个字符
        System.out.println("gensalt = " + gensalt);
        String str = "123456";
        String encStr = BCrypt.hashpw(str, gensalt);
        System.out.println("encStr = " + encStr);  // 加密后的前29位就是盐

        boolean check = BCrypt.checkpw(str, encStr);
        System.out.println("check = " + check);



    }
}
