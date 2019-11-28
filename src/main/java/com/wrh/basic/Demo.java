package com.wrh.basic;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:23 2018/9/5 0005
 * @Modified By:
 */
@Slf4j
public class Demo {

    public static void main(String[] args) {
        SuperClass clz = new SubClass();
        System.out.println(clz.name);
    }

    @Test
    public void test(){
        AVo a = new AVo();
        a.setNum(1);
        a.setName("a");

        log.info("===>{}", JSON.toJSONString(a));
        a.setHash();
        log.info("===>{}", JSON.toJSONString(a));


    }
}
