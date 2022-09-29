package com.wrh;

import org.junit.Test;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/14 11:55
 */
public class python {
    @Test
    public void Test10() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a='hello world';");
        interpreter.exec("print a");
    }

    @Test
    public void Test20() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\pro\\pythonpro\\javaPythonFile.py");
    }
    
    @Test
    public void Test26() throws IOException, InterruptedException {
        Process proc;
        proc = Runtime.getRuntime().exec("python D:\\pro\\pythonpro\\demo2.py");

        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
        proc.waitFor();
    }

    @Test
    public void Test44() {
        int a = 18;
        int b = 23;
        try {
            String[] args1 = new String[] { "python", "D:\\pro\\pythonpro\\demo2.py", String.valueOf(a), String.valueOf(b) };
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getType(Object o){ //获取变bai量类型方法du
        return o.getClass().toString(); //使用int类型的getClass()方法
    }

    @Test
    public void Test66() throws Exception {
        String cmds = String.format("python D:\\pro\\pythonpro\\demo3.py %s","中国");
        Process pcs = Runtime.getRuntime().exec(cmds);
        pcs.waitFor();

        BufferedReader in = new BufferedReader(new InputStreamReader(pcs.getInputStream(),"GB2312"));

        Map<String, String> map = new HashMap<>();
        String line = null;
        System.out.println(in.readLine());
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            String[] s = line.split("\t");
            System.out.println(s[0]+s[1]);
            map.put(s[0],s[1]);
        }
        System.out.println(in.readLine());
        if (in.readLine() == null){
            System.out.println("yes hhhhhh");
        }
//        String key1 = (String) map.keySet().toArray()[0];
        String key1 = (String) map.keySet().toArray()[0];
        String d1 = map.get(key1);
        double xx = Double.parseDouble(d1);
      
    }
}
