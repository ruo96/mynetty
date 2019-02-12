package com.wrh.thread.Atomic;







import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:50 2019/2/12 0012
 * @Modified By:
 */
public class TestAtomicCount {

    private AtomicInteger atomicI = new AtomicInteger(0);

    private    int i = 0;

    public static void main(String[] args) {
        final TestAtomicCount cas = new TestAtomicCount();

        List<Thread> ts = new ArrayList<>(600);

        long start = System.currentTimeMillis();
        for(int j = 0;j<100;j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0 ; i<10000;i++){

                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);

        }
        for(Thread t: ts ){
            t.start();
        }

        for(Thread t: ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);

    }

    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if(suc){
//                System.out.println("safe i : " + i);
                break;
            }
        }
    }

    private void  count(){
        i++;
//        System.out.println("unsafe i : " + i);
    }
}
