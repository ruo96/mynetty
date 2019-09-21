package com.wrh.reference.softReference;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:13 2019/8/30 0030
 * @Modified By:
 */
@Slf4j
public class TestSoftReference {
    @Test
    public void testReference() throws InterruptedException {
        String str = new String(":abc");

        SoftReference<String> softReference = new SoftReference<>(str); //软引用

        log.info("软引用的值： {}",softReference.get());
        str = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        log.info("软引用的值： {}",softReference.get());
        log.info("str： {}",str);


    }
}
