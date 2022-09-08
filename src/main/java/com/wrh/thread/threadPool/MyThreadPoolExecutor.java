package com.wrh.thread.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * @author wuruohong
 * @Classname MyThreadPoolExecutor
 * @Description TODO
 * @Date 2020/8/4 20:51
 */
@Slf4j
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private boolean hasFinish = false;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    private static final ThreadPoolExecutor pool;

    /**
     * 自定义线程池
     */
    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("po-detail-pool-%d").build();
        ThreadFactory threadFactory1 = new CustomizableThreadFactory("custom-thread-pool");
        pool = new ThreadPoolExecutor(4, 8, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(512),
                threadFactory, new ThreadPoolExecutor.AbortPolicy());
        pool.allowCoreThreadTimeOut(true);

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t){
        super.afterExecute(r,t);
        synchronized (this){
            log.info("自动调用... afterExecute , getActionCount: {}", this.getActiveCount());
            if(this.getActiveCount() == 1){
                this.hasFinish = true;
                this.notify();
            }else {
                log.info(">>> exception : {}", t);
            }

            isEndTask();

            if(t != null){

                log.info(">>>  t thread: {} exception : {}", Thread.currentThread().getName(), t);
            }
        }
    }

    public void isEndTask(){
        synchronized (this){
            while (this.hasFinish == false){
                log.info(">>> 等待线程池所有任务结束： wait...");
                try{
                    this.wait();
                }catch (InterruptedException e){
                    log.info(">>> exception: {}", e);
                }
            }
        }
    }
}
