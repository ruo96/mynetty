package com.wrh.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestVelocity
 * @Description TODO
 * @Date 2020/12/23 16:44
 */
@Slf4j
public class TestVelocity {

    @Test
    public void Test15() {
        List<Integer> list = new ArrayList<>();
        list.add(987);
        list.add(23);
        list.add(11);
        list.add(452);
        list.add(23);
        list.add(567);
        list.add(8976);
        list.add(1234);
        list.add(678);

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        Long time1 = System.currentTimeMillis();
        long startMemory1 = Runtime.getRuntime().freeMemory();
        List<Integer> list1 = list.stream().limit(10).collect(Collectors.toList());
        long startMemory2 = Runtime.getRuntime().freeMemory();
        Long time2 = System.currentTimeMillis();

        Long time3 = System.currentTimeMillis();
        long startMemory3 = Runtime.getRuntime().freeMemory();
        List<Integer> list2 = list.parallelStream().limit(10).collect(Collectors.toList());
        long startMemory4 = Runtime.getRuntime().freeMemory();
        Long time4 = System.currentTimeMillis();
        System.out.println(list1);
        System.out.println(list2);

        System.out.println(time2-time1);
        System.out.println(time4-time3);
        System.out.println("================================");
        System.out.println(startMemory1 - startMemory2);
        System.out.println(startMemory3 - startMemory4);

    }
    @Test
    public void Test62() throws IOException {
        System.out.println(execDOStoOutPut("systeminfo"));

    }

    public static String execDOStoOutPut(String command) throws IOException {
        String result = "";
        InputStream is = null;
        InputStreamReader ir = null;
        BufferedReader bufferedReader = null;
        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command);
            // 创建一个使用默认大小输入缓冲区的缓冲字符输入流
            is = process.getInputStream();
            ir = new InputStreamReader(is);
            bufferedReader = new BufferedReader(ir);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                result += temp;
                System.out.println(temp);
            }
            process.waitFor();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                bufferedReader.close();
                ir.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
