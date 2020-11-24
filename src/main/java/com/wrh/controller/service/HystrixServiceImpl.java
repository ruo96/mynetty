package com.wrh.controller.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname HystrixServiceImpl
 * @Description TODO
 * @Date 2020/8/14 11:20
 */
@Slf4j
@Service
public class HystrixServiceImpl implements HystrixService {


    @Override
    @HystrixCommand(fallbackMethod = "hystrixFallback"
            /*threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"), @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},*/
            /*commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "1"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "60000")
            }*/)
    public String hello() {
        log.info(">>> begin enter sleep, now: {}", LocalDateTime.now().toString());
        throw new NullPointerException();
        /*try {

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        return "hello";
    }

    public String hystrixFallback() {
        log.info(">>> hystrixFallback :{}", LocalDateTime.now());
        return "服务器负载过重，请稍后请求!";
    }
}
