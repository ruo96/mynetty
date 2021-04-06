package com.wrh.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname AsyncServiceImpl
 * @Description TODO
 * @Date 2021/4/6 11:23
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Async
    @Override
    public void getAsyncMsg()  {
        log.info(">>> async service get start! {}", System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>> async service get end! {}", System.currentTimeMillis());
    }
}
