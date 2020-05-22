package com.wrh.password;

import com.wrh.utils.MyPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wuruohong
 * @Classname TestPasswordEncode
 * @Description TODO
 * @Date 2020/5/21 19:48
 */
@Slf4j
public class TestPasswordEncode {

    /**
     * 两种加密方式
     */
    @Test
    public void Test1() {
        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        log.info("{}after encode: {}", "123", encode);
        log.info(" 123 match: {}", passwordEncoder.matches("123", encode));
    }

    @Test
    public void Test2() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String str = "123456789abcdefghijklmn";
        String encodePasswd = passwordEncoder.encode(str);
        log.info("{}after encode: {}", str, encodePasswd);  // 123after encode: $2a$10$8hg8tjriuuLwbbm7xuZ1MOgoxgQ01r0YDPZMDOjLIgG.MEq4hiqKu
                                                                              //$2a$10$8ywb1V4buvpDni6Bf3IYVObchm4XafQaiB1irGU8i0xlH5K1rdxxi
        log.info(" 123 match: {}", passwordEncoder.matches(str, encodePasswd));
    }
}
