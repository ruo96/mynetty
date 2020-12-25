package com.wrh.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestDomain
 * @Description TODO
 * @Date 2020/12/23 18:04
 */
@Slf4j
public class TestDomain {
    @Test
    public void Test14() {
       GamePeriodVo v = new GamePeriodVo();
        if (v.isValid()) {
            System.out.println("v is ture");
        } else {
            System.out.println("v is not true");
        }

    }
}
