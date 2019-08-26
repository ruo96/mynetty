package com.wrh.calculate.MapRecordCal;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:46 2019/4/26 0026
 * @Modified By:
 */
public class TestMapRecordCal {
    public static void main(String[] args) throws IOException {
        Runtime run = Runtime.getRuntime();
        long startMem = run.totalMemory() - run.freeMemory();
        System.out.println("memory> total:" + run.totalMemory() + " free:" + run.freeMemory() + " used:" + startMem );

        Map<String,String> map = new HashMap<>(120000);
        String path = "/home/auto-3981-112900000220190419008.txt";
        FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);

        String line;
        String qrcode;
        int i = 1;
        while((line = br.readLine()) != null){
            qrcode = line.split("/q/")[1];
            map.put(qrcode,"pentoupentou001" + (i%3));
            i++;
        }


        long endMem = run.totalMemory()-run.freeMemory();
        System.out.println("memory> total:" + run.totalMemory() + " free:" + run.freeMemory() + " used:" + endMem );

        System.out.println("memory difference:" + (endMem-startMem));

        System.out.println("map size: "+ map.size());
        Iterator<Map.Entry<String,String>> entryIterator = map.entrySet().iterator();
        for (int j = 0; j < 10; j++) {
            Map.Entry<String,String> entry = entryIterator.next();
            System.out.println(entry.getKey()+"========"+entry.getValue());
        }



    }
}
