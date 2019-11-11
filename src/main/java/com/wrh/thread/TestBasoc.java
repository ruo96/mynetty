package com.wrh.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:31 2018/11/15 0015
 * @Modified By:
 */
@Slf4j
public class TestBasoc {

    private static void readBook(){
        for (; ; ) {

            log.info("read book!");
        }
    }

    private static void watchMovie(){
        for (; ; ) {

            log.info("watch movie!");
        }
    }

    public static void main(String[] args) {
        new Thread(TestBasoc::readBook).start();
//        new Thread(TestBasoc::watchMovie).start();
        watchMovie();
    }

    @Test
    public void test(){

    }
}
