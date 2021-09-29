package com.wrh.thread.threadLocal;

import io.netty.util.concurrent.FastThreadLocalThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:13 2019/11/27 0027
 * @Modified By:
 */
@Slf4j
public class TestThreadLocal {

    @Test
    public void test(){
        Thread thread = Thread.currentThread();
        ThreadLocal threadLocal;
    }

    @Test
    public void Test22() {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();

    }

    @Test
    public void Test32() throws InterruptedException {
        InheritableThreadLocal<Object> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
            threadLocal.remove();
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());

        FastThreadLocalThread fastThreadLocalThread;

    }
    
    @Test
    public void Test53() {
        //  为了复现key被回收的场景，我们使用临时变量
        ThreadLocalMemory memeory = new ThreadLocalMemory();

       /* // 调用
        incrementSameThreadId(memeory);

        System.out.println("GC前：key:" + memeory.threadId);
        System.out.println("GC前：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 设置为null，调用gc并不一定触发垃圾回收，但是可以通过java提供的一些工具进行手工触发gc回收。
        memeory.threadId = null;
        System.gc();

        System.out.println("GC后：key:" + memeory.threadId);
        System.out.println("GC后：value-size:" + refelectThreadLocals(Thread.currentThread()));*/

        // 模拟线程一直运行
        while (true) {
        }
        
    }

    private static void incrementSameThreadId() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread() + "_" + i + ",threadId:" + ThreadLocalId.get());
            }
        }
        finally {
            ThreadLocalId.remove();
        }
    }


}
