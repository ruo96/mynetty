package com.wrh.threadlocal.test1;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:35 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class SeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };

    public int nextSeq(){
        seqCount.set(seqCount.get() + 1);
        return seqCount.get();
    }

    public static class SeqThread extends Thread{
        private SeqCount seqCount;

        public SeqThread(SeqCount seqCount){
            this.seqCount = seqCount;
        }

        @Override
        public void run(){
            for (int i = 0; i < 3 ; i++) {
                log.info("thread: {} count: {}",Thread.currentThread().getName(), seqCount.nextSeq());
            }
        }

    }

    public static void main(String[] args) {
        SeqCount seqCount = new SeqCount();

        SeqThread seqThread1 = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();

    }
}
