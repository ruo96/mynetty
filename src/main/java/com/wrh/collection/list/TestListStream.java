package com.wrh.collection.list;

import com.wrh.collection.map.TestMap;
import com.wrh.elasticsearch.Student;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:47 2019/7/19 0019
 * @Modified By:
 */
public class TestListStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.stream().forEach(e->{
            if(e == 1){
                return;
            }else {
                System.out.println("===>" + e);
            }
        });

        char a = 'a';
        System.out.println(Integer.valueOf(a));

        int b = 5;
        char c = (char) b;
        System.out.println(c);

        int d = 10;
        char e = (char)((d/10) +97);
        String f = String.valueOf(e);
        System.out.println(f);

        String g = "编号:4603-名字:佛珠-大-小--名字:123";
        System.out.println("===> index: "+g.indexOf("-名字:"));
        System.out.println("===> name: " + g.substring(g.indexOf("-名字:") + 4));
        String[] gs = StringUtils.split(g,"名字");
        for (int i = 0; i < gs.length; i++) {
            System.out.println(gs[i]);
        }
    }

    public static final String SEPARATOR_COLON = ":";
    public static final String SEPARATOR_MINUS = "-";

    @Test
    public void Test1() {
        List<GameTopOffline> offlines = new ArrayList<>();
        GameTopOffline g1 = new GameTopOffline();
        g1.setGameBaseId(0);
        g1.setIncome(0L);

        GameTopOffline g2 = new GameTopOffline();
        g2.setGameBaseId(1);
        g2.setIncome(1L);

        GameTopOffline g3 = new GameTopOffline();
        g3.setGameBaseId(2);
        g3.setIncome(2L);

        offlines.add(g1);
        offlines.add(g2);
        offlines.add(g3);

        System.out.println(getRedisFormatData(offlines));

        String str = getRedisFormatData(offlines);

        List<GameTopOffline> newList = handleOfflineRedisData(str);
        System.out.println(newList);



    }

    private List<GameTopOffline> handleOfflineRedisData(String redisData) {
        List<GameTopOffline> result = new ArrayList<>();
        String[] offline = redisData.split(SEPARATOR_MINUS);
        Arrays.asList(offline).stream().forEach(e->{
            String[] single = e.split(SEPARATOR_COLON);
            GameTopOffline g = new GameTopOffline();
            g.setGameBaseId(Integer.valueOf(single[0]));
            g.setIncome(Long.valueOf(single[1]));
            result.add(g);
        });
        return result;
    }

    private String getRedisFormatData(List<GameTopOffline> list){
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(e->{
            sb.append(e.getGameBaseId()).append(SEPARATOR_COLON).append(e.getIncome()).append(SEPARATOR_MINUS);
        });
        String str = sb.toString();
        return str.substring(0, str.length() - 1);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        list.add("w4");
        return list;
    }

    private List<Student> getStudentList(){
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);

        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setGrade(3);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        return list;
    }


    @Test
    public void Test107() {
//        List<Student> list = getStudentList();
        List<Student> list = new ArrayList<>();
        Map<Integer, List<Student>> offlineKpiMap = new HashMap<>();
        offlineKpiMap = list.stream().collect(Collectors.groupingBy(Student::getId));
        System.out.println(offlineKpiMap);
        System.out.println(offlineKpiMap.size());
        System.out.println(offlineKpiMap == null);

    }

    @Test
    public void Test156() {
        List<Student> list = getStudentList();
        Long a = list.stream().filter(e->e.getName().equals("w4")).mapToLong(Student::getGrade).sum();
        System.out.println(a);
    }

    @Test
    public void Test161() {
//        long monthTarget = Stream.of(1,2,3).limit(2).sum();
//
        List<Long> monthTarget = Arrays.asList(1L,2L,3L,4L,5L,6L,7L);
        long a = monthTarget.stream().limit(4).mapToLong(Long::longValue).sum();
        System.out.println("a = " + a);

        long monthTarget1 = Stream.of(1L,2L,3L,4L,5L,6L,7L).limit(5).mapToLong(Long::longValue).sum();
        System.out.println("monthTarget1 = " + monthTarget1);

    }

}
