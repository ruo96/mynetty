package com.wrh.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wuruohong
 * @Classname AsyncConfiguration
 * @Description @Async使用的线程池其中一种方式  另外一种是  继承AsyncConfigurerSupport  还有：配置由自定义的TaskExecutor替代内置的任务执行器
 * @Date 2021/11/11 15:18
 */
@Configuration
// 需要使用时候打开
public class AsyncConfiguration implements AsyncConfigurer {

    @Bean("kingAsyncExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int corePoolSize = 10;
        executor.setCorePoolSize(corePoolSize);
        int maxPoolSize = 50;
        executor.setMaxPoolSize(maxPoolSize);
        int queueCapacity = 10;
        executor.setQueueCapacity(queueCapacity);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        String threadNamePrefix = "kingDeeAsyncExecutor-";
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 使用自定义的跨线程的请求级别线程工厂类19
        int awaitTerminationSeconds = 5;
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        executor.initialize();
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
        //return (ex, method, params) -> ErrorLogger.getInstance().log(String.format("执行异步任务'%s'", method), ex);
    }
}
