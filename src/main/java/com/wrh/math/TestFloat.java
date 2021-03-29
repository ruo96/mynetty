package com.wrh.math;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:37 2019/10/12 0012
 * @Modified By:
 */
@Slf4j
public class TestFloat {

    @Test
    public void test(){
        float f=3.4F;
        log.info("===> {}",f);
    }

    @Test
    public void Test21() {
        String s = "";
        int a = NumberUtil.parseInt(s);
        System.out.println(a);

    }
}
