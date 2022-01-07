package com.wrh.reference.weakreference;

import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:11 2018/9/28 0028
 * @Modified By:
 */
public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference r = new WeakReference(new String("i am here!"));

        WeakReference sr = new WeakReference("i am here!");

        String str = new String("i am still here");

        WeakReference<String> weakReference = new WeakReference<>(str);

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after gc: r = "+r.get()+" , static = " + sr.get());
        System.out.println("weakReference.get() = " + weakReference.get()); // 存在强引用，因此无法回收
    }

    @Test
    public void Test34() {
        // threadLocal在线程内，属于线程内部变量， 因此希望线程生命周期结束之后，threadlocal也随之消失，那么这个Entry也会对应的消失掉，从而回收Entry的内存,这在单线程中是可以实现的
        // 但是如果是线程池的情况，那么thread就不会消失，从而threadlocalMap也不会消失，那么 threadLocalMap持有的Entry也不会消失
        /**
         * 所以我们总结了使用ThreadLocal时会发生内存泄漏的前提条件：
         *
         * ①ThreadLocal引用被设置为null，且后面没有set，get,remove操作。
         * ②线程一直运行，不停止。（线程池）
         * ③触发了垃圾回收。（Minor GC或Full GC）
         *
         * 我们看到ThreadLocal出现内存泄漏条件还是很苛刻的，所以我们只要破坏其中一个条件就可以避免内存泄漏，单但为了更好的避免这种情况的发生我们使用ThreadLocal时遵守以下两个小原则:
         *
         * ①ThreadLocal申明为private static final。
         * Private与final 尽可能不让他人修改变更引用，
         * Static 表示为类属性，只有在程序结束才会被回收。
         * ②ThreadLocal使用后务必调用remove方法。
         * 最简单有效的方法是使用后将其移除。
         */

        //  为了复现key被回收的场景，我们使用临时变量
        ThreadLocalMemory memeory = new ThreadLocalMemory();

        // 调用
        //incrementSameThreadId(memeory);

        System.out.println("GC前：key:" + memeory.threadId);
        //System.out.println("GC前：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 设置为null，调用gc并不一定触发垃圾回收，但是可以通过java提供的一些工具进行手工触发gc回收。
        memeory.threadId = null;
        System.gc();

        System.out.println("GC后：key:" + memeory.threadId);
        //System.out.println("GC后：value-size:" + refelectThreadLocals(Thread.currentThread()));

        // 模拟线程一直运行
        while (true) {
        }

    }


}
