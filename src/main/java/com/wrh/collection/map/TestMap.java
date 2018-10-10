package com.wrh.collection.map;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:14 2018/9/17 0017
 * @Modified By:
 */
public class TestMap {

    public static String getAccountIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        String prefix = "user";
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        return prefix + String.format("%015d", hashCodeV);
    }



    public static void main(String[] args) {
        /*Map<String, Object> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");

        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());

        Integer mm = 111;
        Field field = null;
//        try{
            field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(mm,101);
            Integer nn = 128;
            System.out.println(mm);
            System.out.println(nn);
        }catch (Exception e){
            e.printStackTrace();
        }*/


        /*System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            String s = UUID.randomUUID().toString();
//            s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
            System.out.println(s.substring(0, 12));
            System.out.println(s);
        }
*/
        System.out.println(TestMap.getAccountIdByUUId());

        try {
            Thread.sleep(1000L);
            System.out.println("sleep done");
            return;
        } catch (InterruptedException e) {
            System.out.println("catch exception");
        }finally {
            System.out.println("finally");
        }


    }
}
