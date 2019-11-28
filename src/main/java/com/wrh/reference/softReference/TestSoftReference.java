package com.wrh.reference.softReference;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:13 2019/8/30 0030
 * @Modified By:
 */
@Slf4j
public class TestSoftReference {
    @Test
    public void testReference() throws InterruptedException {
        String str = new String(":abc");

        SoftReference<String> softReference = new SoftReference<>(str); //软引用

        log.info("软引用的值： {}",softReference.get());
        str = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        log.info("软引用的值： {}",softReference.get());
        log.info("str： {}",str);
    }

    /**
     * 强引用：使用最普遍的引用，一般情况下，垃圾回收器绝对不会回收它。内存不足时，抛出OOM
     * 软引用：内存空间足够，垃圾回收器不会回收它。反之，则回收。适用于缓存，而且不会OOM。
     * 弱引用：只有当垃圾回收器扫描到弱引用指向的对象时，才会回收它。生命周期比软引用更短。ThreadLocal的key使用了弱引用。
     * 虚引用：在任何时候都可能被垃圾回收器回收，必须与引用队列关联使用。
     */
    @Test
    public void test(){
        SoftReference<Object[]> reference = new SoftReference<>(new Object[300000000]);
        System.out.println(reference.get());
        Object[] objects = new Object[400000000];// 3
        System.out.println(reference.get());
    }
    @Test
    public void test1(){
        WeakReference<String> reference = new WeakReference<>(new String("hello"));
        System.out.println(reference.get());
        System.gc(); //垃圾回收
        System.out.println(reference.get());
    }

    @Test
    public void test2(){
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> reference = new PhantomReference<>(new String("hello"), queue);
        System.out.println(reference.get());
        System.out.println(queue.poll());
    }

    @Test
    public void test3(){
        SoftReference softReference = new SoftReference("123");
        System.out.println(softReference.get());
    }
    
}
