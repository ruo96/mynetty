package com.wrh.controller.service;

import com.google.common.util.concurrent.RateLimiter;
import com.wrh.annotate.annotation.ConcurrentRateLimiter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname HelloServiceImpl
 * @Description TODO
 * @Date 2020/7/10 14:46
 */
@Service
public class HelloServiceImpl implements HelloService {

//    RateLimiter r = RateLimiter.create(1);


    @Override
    @ConcurrentRateLimiter
    public String hello() {
//        r.acquire();
        System.out.println(LocalDateTime.now().toString() + " begin req");
        return "hello this is helloservice";
    }
}
