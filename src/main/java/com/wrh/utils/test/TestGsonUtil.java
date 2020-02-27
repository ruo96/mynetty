package com.wrh.utils.test;

import clojure.lang.IFn;
import cn.hutool.Hutool;
import com.alibaba.fastjson.JSON;
import com.wrh.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestGsonUtil
 * @Description TODO
 * @Date 2020/2/25 17:19
 */
@Slf4j
public class TestGsonUtil {
    @Test
    public void Test() {
        Dog dog = new Dog();
        dog.setColor("black");
        dog.setName("w1");
        dog.setAge(1);

        log.info(">>>> Gson: {}", GsonUtils.GSON.toJson(dog));
        log.info(">>>> fastjson: {}", JSON.toJSONString(dog));


    }
}
