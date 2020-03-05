package com.wrh.utils;

import java.io.*;

/**
 * @author wuruohong
 * @Classname FileUtils
 * @Description TODO
 * @Date 2020/3/4 19:07
 */
public class FileUtils {
    private String path;

    public static String readToString(String path) throws IOException {
        FileInputStream input = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(input,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String context = "";
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            context += line + "\n";
        }
        bufferedReader.close();
        reader.close();
        input.close();
        return context;
    }
    public static void writeString(String content, String path) throws IOException{
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(path);
        OutputStreamWriter writer = new OutputStreamWriter(output,"UTF-8");
        PrintWriter printer = new PrintWriter(writer);
        printer.println(content);
        printer.close();
        writer.close();
        output.close();
    }
}
