package com.wrh.collection.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:43 2019/5/7 0007
 * @Modified By:
 */
@Slf4j
public class TestArrayList {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>(10);
        System.out.println("real size: "+a.size());
        a.add("1");
        System.out.println("real size: "+a.size());


        List<String> b = new ArrayList<>(10);
        b.add("2");
        b.add("3");
        b.add("4");

        a.addAll(b);
        System.out.println("after size: " + a);
        System.out.println("after size string: " + JSON.toJSONString(a));

        long c = 10000;
        Integer d = Math.toIntExact(c);
        AtomicInteger e = new AtomicInteger(1);
        System.out.println(e.incrementAndGet());


        List<String> f = new ArrayList<>(10);
        f.add("1");
        f.add("2");
        f.add("3");
        f.add("4");
        f.add("5");
        f.add("6");
        List<String> f1 = Lists.newArrayList(f.subList(0,3));
        System.out.println("f1 is : "+ JSON.toJSONString(f1));

        List<String> g = new ArrayList<>(10);
        g.add("7");
        g.add("8");
        g.add("9");
//        clearList(g);

        f.addAll(g);
        System.out.println("addall f is : "+ JSON.toJSONString(f));

    }

    private static void clearList(List<String> list){
        list.clear();
    }


    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        int count = 10000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < count; i++) {

            list.add(0,i);
        }
        stopWatch.stop();
        log.info("=== result time  is :{}",stopWatch.getTotalTimeSeconds());

        LinkedList<Integer> list1 = new LinkedList<>();
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        for (int i = 0; i < count; i++) {

            list1.addLast(i);
        }
        stopWatch1.stop();
        log.info("=== result time is :{}",stopWatch1.getTotalTimeSeconds());
    }

    @Test
    public void test1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<Integer> list1 = new ArrayList<>(list);

        list.add(6);

        log.info("===> list: {}",JSON.toJSONString(list));
        log.info("===> list1: {}",JSON.toJSONString(list1));
    }

    @Test
    public void test2(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        

        List<Integer> list1 = getReturnResult(list);

        log.info("===> list: {}",JSON.toJSONString(list));
        log.info("===> list1: {}",JSON.toJSONString(list1));
    }

    private List<Integer> getReturnResult(List<Integer> list) {

        List<Integer> bills = Lists.newArrayList();
        List<Integer> newList = list.stream().map(f->{
            if(f > 3){
                log.info("===> if : {}",f);
                return Lists.newArrayList(9);
            }else {
                log.info("===> else : {}",f);
                bills.add(f);
                log.info("===> else  bills : {}",bills);
                return bills;
            }
        }).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

        return newList;
    }

    /**
     * 简单的list去重
     */
    @Test
    public void test3(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);


        Set<Integer> set = new HashSet<>(list);

        log.info("===> list: {}",JSON.toJSONString(list));
        log.info("===> set: {}",JSON.toJSONString(set));
    }

    @Test
    public void test4(){

        String[] array = new String[]{"w1"};

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);


        Set<Integer> set = new HashSet<>(list);

        log.info("===> list: {}",JSON.toJSONString(list));
        log.info("===> set: {}",JSON.toJSONString(set));
    }

    @Test
    public void test5(){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Set<Integer> set = new HashSet<>(list);

        log.info("===> list: {}",JSON.toJSONString(list));
        log.info("===> set: {}",JSON.toJSONString(set));
    }
    
    @Test
    public void Test1() {
        ArrayList<String> a = new ArrayList<String>();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        System.out.println("Before iterate : " + a);
        ListIterator<String> it = a.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next() + ", " + it.previousIndex() + ", " + it.nextIndex());
        }
        while (it.hasPrevious()) {
            System.out.print(it.previous() + "|");
        }
        System.out.println();
        it = a.listIterator(1);
        while (it.hasNext()) {
            String t = it.next();
            System.out.println(t);
            if ("ccc".equals(t)) {
                it.set("nnn");
            } else {
                it.add("kkk");
            }
        }
        System.out.println("After iterate : " + a);
    }
}
