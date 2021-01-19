package com.wrh.thread.threadLocal;

import io.netty.util.concurrent.FastThreadLocalThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:13 2019/11/27 0027
 * @Modified By:
 */
@Slf4j
public class TestThreadLocal {

    @Test
    public void test(){
        Thread thread = Thread.currentThread();
        ThreadLocal threadLocal;
    }

    @Test
    public void Test22() {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();

    }

    @Test
    public void Test32() throws InterruptedException {
        InheritableThreadLocal<Object> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
            threadLocal.remove();
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());

        FastThreadLocalThread fastThreadLocalThread;

    }


}
