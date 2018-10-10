package com.wrh.reference.weakreference;

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

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after gc: r = "+r.get()+" , static = " + sr.get());
    }
}
