package com.wrh.thread.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wrh.thread.threadPool.task.MyTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:59 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class testThreadPool {

    private static RestTemplate restTemplate;

    @Test
    public void test() throws InterruptedException {
         ExecutorService service = new ThreadPoolExecutor(8,16,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("my_task-%d").build());

//         ExecutorService service1 = new Th

        ExecutorService service1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();
        ExecutorService service4 = Executors.newScheduledThreadPool(10);

        service.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        log.info("线程{} 休息完毕", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        TimeUnit.SECONDS.sleep(4);
        log.info("主线程{}结束",Thread.currentThread().getName());
    }

    /**
     * 强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
     * 说明：Executors 返回的线程池对象的弊端如下：
     * 1） FixedThreadPool 和 SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
     * 2）CachedThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
     */
    @Test
    public void test1(){
//        ExecutorService service = new ThreadPoolExecutor(1,1,);
        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service1 = Executors.newFixedThreadPool(1);
        ExecutorService service2 = Executors.newCachedThreadPool();
        ExecutorService service3 = Executors.newScheduledThreadPool(1);
        ExecutorService service4 = Executors.newSingleThreadExecutor();
    }

    private static class ExceptionHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(">>> 出了异常！！！！");
        }
    }

    private static class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread();
            t.setUncaughtExceptionHandler(new testThreadPool.ExceptionHandler());
            return t;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30),
                Executors.defaultThreadFactory());*/

//        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30));
        MyThreadPoolExecutor pool = new MyThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30));
//        pool.setThreadFactory(new MyThreadFactory());

/*        ExecutorService pool = new ThreadPoolExecutor(8, 16, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(512), Executors.defaultThreadFactory());*/


        Runnable task = () -> {
            log.info(">>> begin :  thread: {}", Thread.currentThread().getName());
            int i1 = 10/0;
            log.info(">>> end :  i1: {}", i1);
        };


        pool.submit(task);
        /*if(future.get()==null){
            log.info("任务无异常");
        }*/

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        log.info(">>> FINAL END");
    }

    /**
     * 线程池的创建方式总共包含以下 7 种（其中 6 种是通过 Executors 创建的，1 种是通过 ThreadPoolExecutor 创建的）：
     * Executors.newFixedThreadPool：创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待；
     * Executors.newCachedThreadPool：创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程；
     * Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执行顺序；
     * Executors.newScheduledThreadPool：创建一个可以执行延迟任务的线程池；
     * Executors.newSingleThreadScheduledExecutor：创建一个单线程的可以执行延迟任务的线程池；
     * Executors.newWorkStealingPool：创建一个抢占式执行的线程池（任务执行顺序不确定）【JDK 1.8 添加】。
     * ThreadPoolExecutor：最原始的创建线程池的方式，它包含了 7 个参数可供设置，后面会详细讲。
     */
    @Test
    public void Test76() {
// 创建 2 个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
            }
        };

        // 线程池执行任务(一次添加 4 个任务)
        // 执行任务的方法有两种:submit 和 execute
        threadPool.submit(runnable);  // 执行方式 1:submit
        threadPool.execute(runnable); // 执行方式 2:execute
        threadPool.execute(runnable); // 执行方式 2:execute
        threadPool.execute(runnable); // 执行方式 2:execute

        // 如果觉得以上方法比较繁琐，还用更简单的使用方法 执行任务
        threadPool.execute(() -> {
            System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
        });

    }

    /**
     * 创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程
     */
    @Test
    public void Test147() {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }

    }

    /**
     * 创建单个线程数的线程池，它可以保证先进先出的执行顺序
     */
    @Test
    public void Test167() {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
    }

    /**
     * 创建一个可以执行延迟任务的线程池
     */
    @Test
    public void Test187() {
// 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时执行任务(1s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }, 1, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程的可以执行延迟任务的线程池
     */
    @Test
    public void Test206() {
// 创建线程池
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        // 添加定时执行任务(2s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }, 2, TimeUnit.SECONDS);
    }

    /**
     * 创建一个抢占式执行的线程池（任务执行顺序不确定），注意此方法只有在 JDK 1.8+ 版本中才能使用
     */
    @Test
    public void Test224() {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {
        }
    }

    /**
     * 最原始的创建线程池的方式，它包含了 7 个参数可供设置
     * 参数 1：corePoolSize            核心线程数，线程池中始终存活的线程数。
     * 参数 2：maximumPoolSize         最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数。
     * 参数 3：keepAliveTime           最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程
     * 参数 4：unit:                   单位是和参数 3 存活时间配合使用的，合在一起用于设定线程的存活时间
     * 参数 5：workQueue               一个阻塞队列，用来存储线程池等待执行的任务，均为线程安全，它包含以下 7 种类型：
     * ArrayBlockingQueue：              一个由数组结构组成的有界阻塞队列。
     * LinkedBlockingQueue：             一个由链表结构组成的有界阻塞队列。
     * SynchronousQueue：一个不存储元素的阻塞队列，即直接提交给线程不保持它们。
     * PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
     * DelayQueue：一个使用优先级队列实现的无界阻塞队列，只有在延迟期满时才能从中提取元素。
     * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。与SynchronousQueue类似，还含有非阻塞方法。
     * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
     * 较常用的是 LinkedBlockingQueue 和 Synchronous，线程池的排队策略与 BlockingQueue 有关。
     * 参数 6：threadFactory 线程工厂，主要用来创建线程，默认为正常优先级、非守护线程。
     * 参数 7：handler 拒绝策略，拒绝处理任务时的策略，系统提供了 4 种可选：
     * AbortPolicy：拒绝并抛出异常。
     * CallerRunsPolicy：使用当前调用的线程来执行此任务。
     * DiscardOldestPolicy：抛弃队列头部（最旧）的一个任务，并执行当前任务。
     * DiscardPolicy：忽略并抛弃当前任务。
     * 默认策略为 AbortPolicy。
     */
    @Test
    public void Test243() {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 自定义拒绝策略
     */
    @Test
    public void Test284() {
// 任务的具体方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前任务被执行,执行时间:" + new Date() +
                        " 执行线程:" + Thread.currentThread().getName());
                try {
                    // 等待 1s
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 创建线程,线程的任务队列的长度为 1
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
                100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        // 执行自定义拒绝策略的相关操作
                        System.out.println("我是自定义拒绝策略~");
                    }
                });
        // 添加并执行 4 个任务
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }

    @Test
    public void Test326() throws InterruptedException {
        int size = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Future<Integer>> futures = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int sleepTime = i;
            Callable<Integer> task = ()->{
                TimeUnit.SECONDS.sleep(sleepTime);
                System.out.println("Thread " + Thread.currentThread().getName() +" done");
                return 1;
            };
            futures.add(executorService.submit(task));
        }
        System.out.println(" for end ");
//        executorService.shutdown();
        TimeUnit.SECONDS.sleep(size-2);
        System.out.println("shut down");
        if (!futures.isEmpty() && futures != null) {
            System.out.println("future done");
        } else {
            System.out.println("future empty");
        }


    }

    @Test
    public void Test357() {
        ExecutorService es = new ThreadPoolExecutor(2,2,0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        MyTask t1 = new MyTask("id:1");
        MyTask t2 = new MyTask("id:2");
        MyTask t3 = new MyTask("id:3");
        MyTask t4 = new MyTask("id:4");
        MyTask t5 = new MyTask("id:5");
        MyTask t6 = new MyTask("id:6");
        MyTask t7 = new MyTask("id:7");
        MyTask t8 = new MyTask("id:8");

        es.execute(t1);
        es.execute(t2);
        es.execute(t3);
        es.execute(t4);
        es.execute(t5);
        es.execute(t6);
        es.execute(t7);
        es.execute(t8);

    }

    @Test
    public void Test379() {
        System.out.println("Thread.currentThread().getContextClassLoader().getClass().getName() = " + Thread.currentThread().getContextClassLoader().getClass().getName());

    }
}
