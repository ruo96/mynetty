package com.wrh.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:44 2019/11/19 0019
 * @Modified By:
 */
@Slf4j
public class TestPool {

    @Test
    public void test(){
        ExecutorService service = new ThreadPoolExecutor(8, 8, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("my thread").build(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
