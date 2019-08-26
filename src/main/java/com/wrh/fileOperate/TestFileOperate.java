package com.wrh.fileOperate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:46 2019/4/4 0004
 * @Modified By:
 */
public class TestFileOperate {
    public static void main(String[] args) throws IOException {
        String path = (new File("/home/zcs/public")).getAbsolutePath();
        System.out.println(path);

        String txt = "123456\n789\n101112";

        Files.write(Paths.get("/home/test.txt"), txt.getBytes(), StandardOpenOption.CREATE);


        String filePath = "e:\\file\\zijuan1.txt";
//        File file = new File(filePath);
        Set<String> set = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                set.add(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("set size is : {}"+set.size());
    }
}
