package com.wrh.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
}
