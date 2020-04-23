package com.wrh.delayjob;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname delay1While
 * @Description 1.无限循环实现延迟任务
 *
 * 此方式我们需要开启一个无限循环一直扫描任务，然后使用一个 Map 集合用来存储任务和延迟执行的时间，实现代码如下：
 * @Date 2020/4/16 18:48
 */
public class delay1While {

    private static Map<String, Long> taskMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序启动时间： " + LocalDateTime.now());
        taskMap.put("task-1", Instant.now().plusSeconds(3).toEpochMilli()); // 延迟3秒

        loopTask();
    }

    /**
     * 无限循环实现延迟任务
     */
    private static void loopTask() throws InterruptedException {
        Long itemLong = 0L;
        while (true) {
            Iterator it = taskMap.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                itemLong = (Long) entry.getValue();

                if (Instant.now().toEpochMilli() >= itemLong) {
                    System.out.println("执行任务: " + entry.getKey() + ",执行时间 ：" + LocalDateTime.now());
                    taskMap.remove(entry.getKey());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

}
