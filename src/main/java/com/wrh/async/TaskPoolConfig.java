package com.wrh.async;

import org.springframework.aop.interceptor.AsyncExecutionAspectSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wuruohong
 * @Classname TaskPoolConfig
 * @Description @Async使用时候的第三种方法， 可以把线程池名称直接放在注解后面 @Async("new_task")
 * @Date 2021/11/11 15:25
 */
@Configuration
// 使用的时候打开就可以了
public class TaskPoolConfig {
    @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(10);
        //最大线程数
        executor.setMaxPoolSize(20);
        //队列容量
        executor.setQueueCapacity(200);
        //活跃时间
        executor.setKeepAliveSeconds(60);
        //线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean(name = "new_task")
    public Executor taskExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(10);
        //最大线程数
        executor.setMaxPoolSize(20);
        //队列容量
        executor.setQueueCapacity(200);
        //活跃时间
        executor.setKeepAliveSeconds(60);
        //线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
