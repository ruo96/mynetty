package com.wrh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:14 2019/7/26 0026
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BasicTest {
    @Test
    public void hello(){
        System.out.println("hello");
    }
}
