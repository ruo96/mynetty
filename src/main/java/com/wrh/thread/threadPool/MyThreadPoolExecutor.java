package com.wrh.thread.threadPool;

import lombok.extern.slf4j.Slf4j;

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
