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

    /**
     * 自定义线程池，可对系统中线程池更加细粒度的控制，方便调整线程池大小配置，线程执行异常控制和处理。
     * 在设置系统自定义线程池代替默认线程池时，虽可通过多种模式设置，但替换默认线程池最终产生的线程池有且只能设置一个（不能设置多个类继承AsyncConfigurer）自定义线程池有如下模式：
     *
     * 重新实现接口AsyncConfigurer
     * 继承AsyncConfigurerSupport
     * 配置由自定义的TaskExecutor替代内置的任务执行器
     * 通过查看Spring源码关于@Async的默认调用规则，会优先查询源码中实现AsyncConfigurer这个接口的类，
     * 实现这个接口的类为AsyncConfigurerSupport。但默认配置的线程池和异步处理方法均为空，
     * 所以，无论是继承或者重新实现接口，都需指定一个线程池。且重新实现 public Executor getAsyncExecutor()方法。
     */
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
