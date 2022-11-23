package com.wrh.queue.blockQueue;

import com.wrh.elasticsearch.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:03 2019/11/28 0028
 * @Modified By:
 */
@Slf4j
public class TestBlockQueue {
    @Test
    public void test() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(3);
        queue.put(1);
        queue.put(2);
        queue.put(3);
        log.info("===> size;;:{}", queue.size());
//        queue.put(4);
        log.info("===> queue.peek:{}", queue.peek());
        Integer i = queue.poll();
        log.info("===> size:{}", queue.size());
        log.info("===> queue.peek:{}", queue.peek());
        log.info("===> i:{}",i);
    }

    /**
     * 优先队列  按照排序获取队列数据
     */
    @Test
    public void Test32() {
        PriorityQueue queue = new PriorityQueue(10, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getGrade().compareTo(o2.getGrade());
            }
        });

        queue.offer(new Student("w1",1,60,1L,"s1",false, LocalDateTime.now()));
        queue.offer(new Student("w2",1,30,1L,"s2",false, LocalDateTime.now()));
        queue.offer(new Student("w3",1,50,1L,"s3",false, LocalDateTime.now()));

        while (!queue.isEmpty()){
            Student s = (Student) queue.poll();
            System.out.println(s);

        }

    }

    private static DelayQueue delayQueue = new DelayQueue();

    /**
     * 延迟队列
     */
    @Test
    public void Test59() throws InterruptedException {
        producer(); // 生产者
        consumer(); // 消费者

    }

    private void consumer() throws InterruptedException {
        System.out.println("开始执行时间： " +
                DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayQueue.isEmpty()){
            System.out.println(delayQueue.take() + "------" + DateFormat.getDateTimeInstance().format(new Date()));
        }
        System.out.println("任务结束时间: " +
                DateFormat.getDateTimeInstance().format(new Date()));
    }

    private void producer() {
        delayQueue.put(new MyDelay(3000, "消息1"));
        delayQueue.put(new MyDelay(1000, "消息2"));
    }

    static class MyDelay implements Delayed{

        // 延迟截止时间 单位： 毫秒
        long delayTime = System.currentTimeMillis();

        @Getter
        @Setter
        private String msg;

        public MyDelay(long delayTime, String msg) {
            this.delayTime = this.delayTime + delayTime;
            this.msg = msg;
        }

        // 获取剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        // 队列里面元素的排序依据
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }

    @Test
    public void Test132() throws InterruptedException {
        long delayTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long result = TimeUnit.MILLISECONDS.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        System.out.println(result);

    }

    @Test
    public void Test141() {
        SynchronousQueue queue = new SynchronousQueue();

        new Thread(()->{
            for (int i = 0; i < 3 ; i++) {
                System.out.println(new Date() + ", 元素入队");
                try {
                    queue.put("Data " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + ", 元素出队:" + queue.take() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
