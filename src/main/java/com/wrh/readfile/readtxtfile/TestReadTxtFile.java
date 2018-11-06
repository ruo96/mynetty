package com.wrh.readfile.readtxtfile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:22 2018/11/6 0006
 * @Modified By:
 */
public class TestReadTxtFile {
    public static void main(String[] args) {
        String filePath = "e:\\file\\1.txt";
//        File file = new File(filePath);
        try(FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
                writeFile(transform(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String transform(String in){
//        String out ;
        String regex_num ="^(\\-|\\+)?\\d+(\\.\\d+)?$";
        String regex_email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern p = Pattern.compile(regex_num);
        Matcher m = p.matcher(in);

        Pattern p1 = Pattern.compile(regex_email);
        Matcher m1 = p1.matcher(in);

        if(m.matches()){
            return "num:"+in;

        }else {
            if(m1.matches()){
                return "email:" + in;
            }
            return "non:"+in;
        }

    }

    public static void writeFile(String inResult){
        File file = new File("e:\\file\\2.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            try(FileWriter writer = new FileWriter(file,true);
                BufferedWriter out = new BufferedWriter(writer)){
                out.write(inResult);
                out.write("\r\n");
                out.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
