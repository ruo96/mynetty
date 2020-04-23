package com.wrh.delayjob;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname delay3DelayQueue
 * @Description DelayQueue 是一个支持延时获取元素的无界阻塞队列，队列中的元素必须实现 Delayed 接口，并重写 getDelay(TimeUnit) 和 compareTo(Delayed) 方法，
 * DelayQueue 实现延迟队列的完整代码如下：
 * @Date 2020/4/16 19:12
 */
public class delay3DelayQueue {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();

        delayQueue.put(new DelayElement(1000));
        delayQueue.put(new DelayElement(3000));
        delayQueue.put(new DelayElement(5000));

        System.out.println("开始时间： " + DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayQueue.isEmpty()) {
            System.out.println(delayQueue.take());
        }
        System.out.println("结束时间： " + DateFormat.getDateTimeInstance().format(new Date()));
    }

    static class DelayElement implements Delayed{

        long delayTime = System.currentTimeMillis();
        public DelayElement(long delayTime) {
            this.delayTime = (this.delayTime + delayTime);
        }

        /**
         * 获取剩余时间
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 队列里元素的排序依据
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return DateFormat.getDateTimeInstance().format(new Date(delayTime));
        }
    }
}
