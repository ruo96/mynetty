package com.wrh.thread.LinkedBlockingQueue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:37 2018/8/22 0022
 * @Modified By:
 */
public class LinkedBlockingQueueTest {

    //    LinkedBlockingQueue
    public static void main(String[] args) {
        final BlockingQueue<File> queue = new LinkedBlockingQueue<>(100);

        final ExecutorService exec = Executors.newFixedThreadPool(5);
        final File root = new File("E:\\block");

        final File exitFile = new File("");

        final AtomicInteger rc = new AtomicInteger();

        final AtomicInteger wc = new AtomicInteger();

        Runnable read = new Runnable() {
            @Override
            public void run() {
                scanFile(root);
                scanFile(exitFile);
            }


            private void scanFile(File file) {

                if(file.isDirectory()){
                    File[] files = file.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.isDirectory()||pathname.getPath().endsWith(".java");
                        }
                    });
                    for(File one:files){
                        scanFile(one);
                    }
                }else {
                    int index = rc.incrementAndGet();
                    System.out.println("Read0: " + index + " "
                      + file.getPath());
                    try {
                        queue.put(file);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        exec.submit(read);
        /*四个写线程*/
        for(int index = 0;index<4;index ++){
            /*写线程*/
            final int NO = index;
            Runnable write = new Runnable() {

                String threadName = "Write" + NO;
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(5000);
                            int index = wc.incrementAndGet();
                            File file = queue.take();
                            /*队列已无对象*/
                            if(file == exitFile){
                                /*再次添加标志, 以让其他线程正常退出*/
                                queue.put(exitFile);
                                break;
                            }
                            System.out.println(threadName + ": " + index + " " + file.getPath());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            exec.submit(write);
        }
        exec.shutdown();
    }

}

