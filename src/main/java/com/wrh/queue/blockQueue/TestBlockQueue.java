package com.wrh.queue.blockQueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:03 2019/11/28 0028
 * @Modified By:
 */
@Slf4j
public class TestBlockQueue {
    @Test
    public void test() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        log.info("===> size;;:{}", queue.size());
//        queue.put(4);
        log.info("===> queue.peek:{}", queue.peek());
        Integer i = queue.poll();
        log.info("===> size:{}", queue.size());
        log.info("===> queue.peek:{}", queue.peek());
        log.info("===> i:{}",i);
    }
}
