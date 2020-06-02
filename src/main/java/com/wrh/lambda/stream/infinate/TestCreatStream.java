package com.wrh.lambda.stream.infinate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author wuruohong
 * @Classname TestCreatStream
 * @Description TODO
 * @Date 2020/6/2 19:15
 */
@Slf4j
public class TestCreatStream {
    /**
     * stream创建无限流
     */
    @Test
    public void Test1() {
        Stream.iterate(0, n -> n+1)
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * Stream在处理原始类型上会由于装箱拆箱造成较大的性能损耗，所以Java8提供了三种特殊的流接口IntStream、DoubleStream、LongStream，
     * 将流中的元素特化为int、double和long。
     */
    @Test
    public void Test2() {
        Stream.of(1, 2, 3)
                .forEach(n -> {
                    System.out.println(n.getClass()); // Integer
                    int x = n * 2; // 需要拆箱
                });

        IntStream.of(1, 2, 3)
                .forEach(n -> {
                    int x = n * 2; // n为int
                });

        /**
         * 除了使用of创建流，还可以将普通流转成数值流，mapToInt、mapToDouble和mapToLong。
         */
        Stream.of(1, 2, 3)
                .mapToInt(Integer::intValue)
                .forEach(n -> {
                    int x = n * 2; // n为int
                });

        /**
         * 数值流也能转成普通流，boxed装箱。
         */
        IntStream.of(1, 2, 3)
                .boxed()
                .forEach(n -> {
                    int x = n * 2; // n为Integer
                });
    }

    /**
     * stream的flatmap  流的扁平化
     */
    @Test
    public void Test3() {
        List<String> list = Arrays.asList("ABC", "DEF", "GHI");
        list.stream()
                .map(s -> s.split("")) // Stream<String[]>
                .forEach(System.out::println);

        List<String> list1 = Arrays.asList("ABC", "DEF", "GHI");
        list1.stream()
                .map(s -> s.split("")) // Stream<String[]>
                .flatMap(Arrays::stream) // Steam<String>
                .forEach(System.out::println);
    }

    /**
     * stream的规约
     */
    @Test
    public void Test4() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int sum = IntStream.of(nums).reduce(0, Integer::sum); // 15
        int max = IntStream.of(nums).reduce(Integer::max).orElse(-1); // 5
        int min = IntStream.of(nums).reduce(Integer::min).orElse(-1); // 1
    }

    /**
     * stream基础操作
     */
    @Test
    public void Test5() {
        List<Paper> papers = Arrays.asList(
                new Paper("小明", "语文", 40),
                new Paper("小明", "数学", 80),
                new Paper("小红", "语文", 80),
                new Paper("小红", "数学", 80),
                new Paper("小蓝", "语文", 50),
                new Paper("小蓝", "数学", 60)
        );
// 所有语文卷子
        List<Paper> chinesePapers = papers.stream()
                .filter(p -> p.getClassName().equals("语文"))
                .collect(toList());
        log.info(">>> chinesePapers : {}" , chinesePapers);
// 所有学科
        Set<String> classNames = papers.stream()
                .map(Paper::getClassName)
                .collect(toSet());
// 最高分的卷子，最低分改成minBy就行
        Paper maxScorePaper = papers.stream()
                .collect(maxBy((p1, p2) -> p1.getScore() - p2.getScore())).get();

        log.info(">>> 最高分的卷子 : {}" , maxScorePaper);
// 总分数
        int sumScore = papers.stream()
                .collect(summingInt(Paper::getScore));
        log.info(">>> 总分数 : {}" , sumScore);
// 平均分
        double avgScore = papers.stream()
                .collect(averagingInt(Paper::getScore));
        log.info(">>> 平均分 : {}" , avgScore);

// 统计数、总和、最大值、最小值和平均值
        IntSummaryStatistics summaryStatistics = papers.stream()
                .collect(summarizingInt(Paper::getScore));
        long count = summaryStatistics.getCount();
        long sum = summaryStatistics.getSum();
        int max = summaryStatistics.getMax();
        int min = summaryStatistics.getMin();
        double avg = summaryStatistics.getAverage();
        log.info(">>> 统计数、总和、最大值、最小值和平均值 : {} {} {} {} {}" , count, sum, max, min, avg);
// 学生名字连接在一起
        String studentNameStr = papers.stream()
                .map(Paper::getStudentName)
                .distinct()
                .collect(joining(","));
        log.info(">>> 学生名字连接在一起 : {}" , studentNameStr);
// 按学科将卷子分组
        Map<String, List<Paper>> groupPapers = papers.stream()
                .collect(groupingBy(Paper::getClassName));
        log.info(">>> 按学科将卷子分组 : {}" , groupPapers);
    }
}
