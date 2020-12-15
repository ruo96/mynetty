package com.wrh.utils.test;

import com.wrh.utils.GuavaCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestGuavaCache
 * @Description TODO
 * @Date 2020/12/14 10:23
 */
@Slf4j
public class TestGuavaCache {
    @Test
    public void Test14() {
        String key1 = "guava_key1";
        GuavaCacheUtil.putValue(key1,"something");
        System.out.println(GuavaCacheUtil.getValue(key1));
        System.out.println(GuavaCacheUtil.getValue(key1));
        System.out.println(GuavaCacheUtil.getValue(key1));
        GuavaCacheUtil.refreshCache(key1);
        System.out.println("================================================");
        System.out.println(GuavaCacheUtil.getValue(key1));
        System.out.println(GuavaCacheUtil.getValue(key1));


    }

    @Test
    public void Test31() {
        String key1 = "guava_key1";
        GuavaCacheUtil.putValue(key1,"something");
        System.out.println("["+GuavaCacheUtil.getValue(key1)+"]");
        System.out.println("["+GuavaCacheUtil.getValue(key1)+"]");
//        GuavaCacheUtil.deleteCache(key1);
        System.out.println("================================================");
        System.out.println("["+GuavaCacheUtil.getValue(key1)+"]");
        System.out.println("["+GuavaCacheUtil.getValue(key1)+"]");
        System.out.println("["+GuavaCacheUtil.stats()+"]");

    }
}
