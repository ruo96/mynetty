package com.wrh.collection.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wuruohong
 * @Classname TestCopyOnWriteArrayList
 * @Description TODO
 * @Date 2020/12/29 11:21
 */
@Slf4j
public class TestCopyOnWriteArrayList {

    /**
     * CopyOnWriteArrayList虽然是一个线程安全版的ArrayList，但其每次修改数据时都会复制一份数据出来，所以只适用读多写少或无锁读场景。     *
     * 所以一旦使用CopyOnWriteArrayList，一定是因为场景适宜而非炫技。
     * 高并发写，CopyOnWriteArray比同步ArrayList慢百倍
     * 高并发写时，CopyOnWriteArrayList为何这么慢呢？因为其每次add时，都用Arrays.copyOf创建新数组，频繁add时内存申请释放性能消耗大。
     */
    @Test
    public void Test14() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        StopWatch stopWatch = new StopWatch();
        int loopCount = 100000;
        stopWatch.start("Write:copyOnWriteArrayList");

        IntStream.rangeClosed(1,loopCount).parallel()
                .forEach(e->copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();

        stopWatch.start("Write:synchronizedList");

        IntStream.rangeClosed(1,loopCount).parallel()
                .forEach(e->synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map result = new HashMap();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
    }

    private void addAll(List<Integer> list) {
        list.addAll(IntStream.rangeClosed(1,100000).boxed().collect(Collectors.toList()));
    }

    @Test
    public void Test55() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        addAll(copyOnWriteArrayList);
        addAll(synchronizedList);


        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        int count = copyOnWriteArrayList.size();
        stopWatch.start("Read:copyOnWriteArrayList");

        IntStream.rangeClosed(1,loopCount).parallel()
                .forEach(e->copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();

        stopWatch.start("Read:synchronizedList");

        IntStream.range(1,loopCount).parallel()
                .forEach(e->synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        Map result = new HashMap();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());

    }
}
