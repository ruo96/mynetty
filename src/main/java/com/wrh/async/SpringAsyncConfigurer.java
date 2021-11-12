package com.wrh.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author wuruohong
 * @Classname SpringAsyncConfigurer
 * @Description @Async使用线程池的第二种方式
 * @Date 2021/11/11 15:23
 */
@Configuration
// 需要使用的时候打开
public class SpringAsyncConfigurer extends AsyncConfigurerSupport {
    @Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(3);
        threadPool.setMaxPoolSize(3);
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        threadPool.setAwaitTerminationSeconds(60 * 15);
        return threadPool;
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        //return (ex, method, params) -> ErrorLogger.getInstance().log(String.format("执行异步任务'%s'", method), ex);
        return null;
    }
}
