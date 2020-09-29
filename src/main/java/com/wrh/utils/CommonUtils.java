package com.wrh.utils;

import cn.hutool.core.collection.ArrayIter;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wrh.collection.map.GameRealTimeData;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname CommonUtils
 * @Description TODO
 * @Date 2020/6/9 10:08
 */
@Slf4j
public class CommonUtils {

    private  String a;
    private String b;
    private int c;

    /**
     * 获取类名
     */
    @Test
    public void Test1() {
        String className = ClassUtils.getPackageName(CommonUtils.class);
        String className1 = ClassUtils.getShortClassName(CommonUtils.class);
        System.out.println(className);
        System.out.println(className1);
    }

    /**
     * 获取所有字段
     */
    @Test
    public void Test2() {
        Field[] fields = FieldUtils.getAllFields(CommonUtils.class);
        System.out.println(fields[0].getName());
        System.out.println(fields[1].getName());
        System.out.println(fields[2].getName());
        System.out.println(fields[3].getName());
     }

    /**
     * 连接
     */
    @Test
     public void Test3() {
         List<String> list = new ArrayList<String>();
         list.add("1");
         list.add("2");
         list.add("3");
         String result = Joiner.on("-").join(list);
         System.out.println(result);
     }

    /**
     * 创建各种集合
     */
    @Test
     public void Test4() {
         List<String> list = Lists.newArrayList();
         Set<String> set = Sets.newHashSet();
         Map<String,Object> map = Maps.newConcurrentMap();

        // 不可变集合
         ImmutableList<String> immutableList = ImmutableList.of("1", "2", "3");
     }

    /**
     * 求交集、并集、差集等
     */
    @Test
     public void Test5() {
         Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5, 6);
         Set<Integer> set2 = Sets.newHashSet(1,2,3,4);

         Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
     }

    /**
     * 一个日期、时间处理的工具库。如果你不是经常做日期处理，那差不多每次需要的时候都需要查询相关的 API，
     * 而有了工具类就不一样了，只要一个 “.”，你想要的方法就出现了，而 Joda Time 就是一款好用的工具库。
     */
    @Test
     public void Test6() {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate newYear = now.plusYears(1).withDayOfYear(1);
        Days days = Days.daysBetween(now, newYear);
        System.out.println(days.getDays());
     }

     public static List<Student> getList(){
         List<Student> list = new ArrayList<>();
         Student s1 = new Student();
         s1.setName("w1");
         s1.setId(1);
         s1.setGrade(1);

         Student s2 = new Student();
         s2.setName("w2");
         s2.setId(2);
         s2.setGrade(2);

         Student s4 = new Student();
         s4.setName("w4");
         s4.setId(2);
         s4.setGrade(4);

         Student s3 = new Student();
         s3.setName("w3");
         s3.setId(3);
         s3.setGrade(3);

         list.add(s1);
         list.add(s2);
         list.add(s3);
         list.add(s4);
         return list;
     }

    /**
     * list转map, 第二种写法很有用
     */
    @Test
     public void Test7() {
        List<Student> list = getList();
        log.info(">>>{}",list);
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(Student::getName, Student::getId));
        System.out.println(map);

        Map<Integer, Student> map1 = list.stream().collect(Collectors.toMap(Student::getId, info->info,(info,info2)->info2));
        System.out.println(map1);
    }

    /**
     * list转map，汇总指定key的参数信息
     */
    @Test
    public void Test152() {
        List<Student> list = getList();
        log.info(">>>{}",list);

        Map<String, Integer> map = new HashMap<>();
        list.forEach(e-> map.merge(e.getName(), e.getGrade(), Integer::sum));
        log.info(">>>{}",map);

       /* Map<String, List<Integer>> map1 = new HashMap<>();
        list.forEach(e-> map1.merge(e.getName(), e.getGrade(), List::add));
        log.info(">>>{}",map1);*/

    }

    @Test
    public void Test8() {
        List<Student> list = getList();
        /*list.stream().map(e->{
            if(e.getGrade() == 1){
                e.setGrade(e.getGrade() + 10);
            }
        }).mapToLong(Student::getGrade).sum();*/

        System.out.println(list);
        final Long[] paySumMoneyArr = {0L};
        list.stream().forEach(e->{
            if(e.getGrade() == 1) {
                paySumMoneyArr[0] += (long) (e.getGrade() * 2);
            }else {
                paySumMoneyArr[0] += e.getGrade();
            }
        });

        System.out.println(paySumMoneyArr[0]);
    }

    public static List<Student> getDuplicateList(){
        List<Student> list = getList();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);

        Student s4 = new Student();
        s4.setName("w4");
        s4.setId(2);
        s4.setGrade(4);

        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setGrade(3);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        return list;
    }

    /**
     * list根据对象字段去重
     */
    @Test
    public void Test177() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 100000; i++) {
            List<Student> list = getDuplicateList();
//            System.out.println(">>>before " + list);
            list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                    -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));
//            System.out.println(">>>after " + list);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

        /** 下面的速度更快一些*/
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        for (int i = 0; i < 100000; i++) {
            List<Student> list1 = getDuplicateList();
//            System.out.println(">>>before " + list1);
            list1 = removeDuplicateOrder(list1);
//            System.out.println(">>>after " + list1);
        }
        stopWatch1.stop();
        System.out.println(stopWatch1.getTotalTimeMillis());

        /** 第三种写法 用来做处理展示或者后续操作*/
        List<Student> list2 = getDuplicateList();
        list2.stream()
                .collect(Collectors.toCollection(()-> new TreeSet<>(Comparator.comparing(Student::getName))))
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void Test236() {
        List<Student> list1 = getDuplicateList();
            System.out.println(">>>before " + list1);
        list1 = removeDuplicateOrder(list1);
            System.out.println(">>>after " + list1);

    }

    /**
     * 这种是list转map的去重
     */
    @Test
    public void Test245() {
        List<Student> list1 = getDuplicateList();
        System.out.println(">>>before " + list1);
        Map<String, Student> map = list1.stream().collect(Collectors.toMap(Student::getName, info->info,(info,info2)->info2));
        System.out.println(">>>after " + map);

    }

    /**
     * 另一种去重方法
     * @param orderList
     * @return
     */
    private static List<Student> removeDuplicateOrder(List<Student> orderList) {
        Set<Student> set = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                // 字符串则按照asicc码升序排列
                return a.getName().compareTo(b.getName());
            }
        });

        set.addAll(orderList);
        return new ArrayList<Student>(set);
    }

    @Test
    public void Test298() {
        List<GameRealTimeData> resultList=new ArrayList<>();
        GameRealTimeData g1 = new GameRealTimeData();
        g1.setFmtDs("2020-09-23");
        g1.setTotalNewAccCnt(100);

        GameRealTimeData g2 = new GameRealTimeData();
        g2.setFmtDs("2020-09-22");
        g2.setTotalNewAccCnt(200);

        resultList.add(g1);
        resultList.add(g2);

        System.out.println(resultList);

        Map<String, Integer> map = new HashMap<>();
        map.put("2020-09-22", 500);

        resultList.stream().forEach(e->{
            if(map.containsKey(e.getFmtDs())){
                e.setTotalNewAccCnt(map.get(e.getFmtDs()));
            }
        });
        System.out.println(resultList);


    }
}
