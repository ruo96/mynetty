package com.wrh.collection.list;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestListSortSupport
 * @Description TODO
 * @Date 2020/5/12 14:15
 */
public class TestListSortSupport {

    public static void main1(String[] args) {

        List<Info> sysListOri = getListSys();
        List<Info> recListOri = getListCon();

        System.out.println(JSONObject.toJSONString(sysListOri));
        System.out.println(JSONObject.toJSONString(recListOri));

        List<Info> syslist = sysListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());
        List<Info> collect = recListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(syslist));
        System.out.println(JSONObject.toJSONString(collect));

        List<Info> newList = new ArrayList<>();
        syslist.stream().filter(i -> {
            if (i.getMsgId() % 3 == 0) {
                newList.add(collect.get(0));
                collect.remove(collect.get(0));
            }
            newList.add(i);
            return true;
        }).limit(8).collect(Collectors.toList());

        System.out.println(">> " + JSONObject.toJSONString(newList));
        System.out.println(newList.stream().mapToInt(Info::getScore).sum());

    }

    private static List<Info> getListSys() {
        List<Info> list = new ArrayList<>(10);
        Info list2 = new Info(2,90,0); list.add(list2);
        Info list3 = new Info(3,80,0); list.add(list3);
        Info list1 = new Info(1,100,0); list.add(list1);
        Info list4 = new Info(4,70,0); list.add(list4);
        Info list5 = new Info(5,60,0); list.add(list5);
        Info list6 = new Info(6,50,0); list.add(list6);
        Info list7 = new Info(7,40,0); list.add(list7);
        Info list8 = new Info(8,30,0); list.add(list8);
        Info list9 = new Info(9,20,0); list.add(list9);
        Info list10 = new Info(10,10,0); list.add(list10);
        return list;
    }

    private static List<Info> getListCon() {
        List<Info> list = new ArrayList<>(10);
        Info list1 = new Info(11,9,1); list.add(list1);
        Info list2 = new Info(12,8,1); list.add(list2);
        Info list3 = new Info(13,7,1); list.add(list3);
        Info list4 = new Info(14,6,1); list.add(list4);
        Info list5 = new Info(15,5,1); list.add(list5);
        Info list6 = new Info(16,4,1); list.add(list6);
        Info list7 = new Info(17,3,1); list.add(list7);
        Info list8 = new Info(18,2,1); list.add(list8);
        Info list9 = new Info(19,1,1); list.add(list9);
        Info list10 = new Info(20,0,1); list.add(list10);
        return list;
    }

  public  static int sysFlag = 0;
    public static void main(String[] args) {

        List<Info> sysListOri = getListSys();
        List<Info> recListOri = getListCon();

        List<Info> syslist = sysListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());
        List<Info> collect = recListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(syslist));
        System.out.println(JSONObject.toJSONString(collect));

        List<Info> result = new ArrayList<>();

        while(result.size() < 10) {
            if(syslist.get(0).getScore() >= collect.get(0).getScore() && sysFlag < 2) {
                result.add(syslist.get(0));
                syslist.remove(0);
                sysFlag += 1;
                System.out.println("添加 queueSys sysFlag: "+sysFlag +"  size: " + result.size());

            } else {
                result.add(collect.get(0));
                collect.remove(0);
                sysFlag = 0;
                System.out.println("添加 queueRec size: "+result.size());
            }
        }

        System.out.println(">> " + JSONObject.toJSONString(result));
        System.out.println(result.stream().mapToInt(Info::getScore).sum());

    }

    public static void main2(String[] args) {

        List<Info> sysListOri = getListSys();
        List<Info> recListOri = getListCon();

        List<Info> syslist = sysListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());
        List<Info> collect = recListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());

        LinkedBlockingQueue<Info> queueSys = new LinkedBlockingQueue();
        syslist.forEach(e-> queueSys.add(e));

        LinkedBlockingQueue<Info> queueRec = new LinkedBlockingQueue();
        collect.forEach(e-> queueRec.add(e));

        List<Info> result = new ArrayList<>();

        while(result.size() < 10) {
            if(queueSys.peek().getScore() >= queueRec.peek().getScore() && sysFlag < 2) {
                result.add(queueSys.poll());
                sysFlag += 1;
                System.out.println("添加 queueSys sysFlag: "+sysFlag +"  size: " + result.size());

            } else {
                result.add(queueRec.poll());
                sysFlag = 0;
                System.out.println("添加 queueRec size: "+result.size());
            }
        }

        System.out.println(">> " + JSONObject.toJSONString(result));
        System.out.println(result.stream().mapToInt(Info::getScore).sum());

    }

    @Test
    public void test$1() {
        List<Info> sysListOri = getListSys();
        System.out.println(sysListOri);
        sysListOri.stream().sorted(Comparator.comparingInt(Info::getScore).reversed()).collect(Collectors.toList());
        System.out.println(sysListOri);
    }

}
