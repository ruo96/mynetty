package com.wrh.algorithum.hanoidtower;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:45 2019/12/4 0004
 * @Modified By:
 */
@Slf4j
public class TestHnoidTower {

    // 将 n 个圆盘从 a 经由 b 移动到 c 上
    public void hanoid(int n, char a, char b, char c) {
        if (n <= 0) {
            return;
        }
        // 将上面的  n-1 个圆盘经由 C 移到 B
        hanoid(n-1, a, c, b);
        // 此时将 A 底下的那块最大的圆盘移到 C
        move(a, c);
        // 再将 B 上的 n-1 个圆盘经由A移到 C上
        hanoid(n-1, b, a, c);
    }

    public void move(char a, char b) {
        log.info("{}->{}", a, b);
    }

    @Test
    public void test1(){
        hanoid(3,'a','b','c');
    }
}
