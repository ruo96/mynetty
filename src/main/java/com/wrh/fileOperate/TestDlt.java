package com.wrh.fileOperate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:46 2019/6/15 0015
 * @Modified By:
 */
public class TestDlt {
    public static void main(String[] args) {
        String filePath = "e:\\data\\daletou.txt";
//        File file = new File(filePath);
        Set<String> set = new HashSet<>();
        List<Daletou> allData = new LinkedList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] str = line.split(" ");
//                List<String> list = Arrays.asList(str);
//                System.out.println("===list: " + list);
                System.out.println("每行数据个数: " + str.length);
                Daletou eachData = new Daletou();
                eachData.setFront_1(str[0]);
                eachData.setFront_2(str[1]);
                eachData.setFront_3(str[2]);
                eachData.setFront_4(str[3]);
                eachData.setFront_5(str[4]);
                eachData.setBack_1(str[5]);
                eachData.setBack_2(str[6]);



                allData.add(eachData);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //开始分析第一个
        Map<String,String> front_1 = new TreeMap<>();
        Map<String,String> front_2 = new TreeMap<>();
        Map<String,String> front_3 = new TreeMap<>();
        Map<String,String> front_4 = new TreeMap<>();
        Map<String,String> front_5 = new TreeMap<>();
        Map<String,String> back_1 = new TreeMap<>();
        Map<String,String> back_2 = new TreeMap<>();

        for(Daletou single: allData){
            handleMap(front_1,single.getFront_1());
            handleMap(front_2,single.getFront_2());
            handleMap(front_3,single.getFront_3());
            handleMap(front_4,single.getFront_4());
            handleMap(front_5,single.getFront_5());
            handleMap(back_1,single.getBack_1());
            handleMap(back_2,single.getBack_2());
        }

        Map<String, String> front_1_map = sortMapByValue(front_1);    //按Key进行排序
        Map<String, String> front_2_map = sortMapByValue(front_2);    //按Key进行排序
        Map<String, String> front_3_map = sortMapByValue(front_3);    //按Key进行排序
        Map<String, String> front_4_map = sortMapByValue(front_4);    //按Key进行排序
        Map<String, String> front_5_map = sortMapByValue(front_5);    //按Key进行排序
        Map<String, String> back_1_map = sortMapByValue(back_1);    //按Key进行排序
        Map<String, String> back_2_map = sortMapByValue(back_2);    //按Key进行排序



        System.out.println("front_1_map is : {}"+front_1_map);
        System.out.println("front_2_map is : {}"+front_2_map);
        System.out.println("front_3_map is : {}"+front_3_map);
        System.out.println("front_4_map is : {}"+front_4_map);
        System.out.println("front_5_map is : {}"+front_5_map);
        System.out.println("back_1_map is : {}"+back_1_map);
        System.out.println("back_2_map is : {}"+back_2_map);


        Map<String,Integer> map = new HashMap<>();
        /*showCodeByBack2("12",allData,map);
        showCodeByBack2("11",allData,map);*/
        showCodeByBack2("12",allData,map);


    }

    private static void showCodeByFirst(String s, List<Daletou> allData) {
        for(Daletou single: allData){
            if(single.getFront_1().equals(s)){
                System.out.println("首位:"+ s + ":" + single);
            }
        }
    }

    private static void showCodeByBack2(String s, List<Daletou> allData,Map<String,Integer> map) {

        for(Daletou single: allData){
            if(single.getBack_2().equals(s)){
                System.out.println("末位:"+ s + ":" + single);
                if(map.containsKey(single.getBack_1())){
                    int v = map.get(single.getBack_1());
                    map.put(single.getBack_1(),v+1);
                }else {
                    map.put(single.getBack_1(),1);
                }
            }
        }
        Map<String, Integer> back_1_sort_map = sortMapByIntValue(map);
        System.out.println("统计的后区前一位数据结果： " + back_1_sort_map);
    }


    private static void handleMap(Map<String, String> front_1, String f1) {

        if(front_1.containsKey(f1)){
            int f1_v = Integer.valueOf(front_1.get(f1));
            front_1.put(f1,String.valueOf(f1_v + 1));
        }else {
            front_1.put(f1,"1");
        }
    }

    private static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    public static Map<String, Integer> sortMapByIntValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapIntValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}
