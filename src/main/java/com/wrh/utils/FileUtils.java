package com.wrh.utils;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wuruohong
 * @Classname FileUtils
 * @Description TODO
 * @Date 2020/3/4 19:07
 */
public class FileUtils {
    private String path;

    /**
     * 提取文件 checksum   用来校验两个文件是否相同
     *
     * @param path      文件全路径
     * @param algorithm  算法名 例如 MD5、SHA-1、SHA-256等
     * @return  checksum
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException              the io exception
     */
    public static String extrackChecksum(String path, String algorithm) throws IOException, NoSuchAlgorithmException {
        // 根据算法名称初始化摘要算法
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        // 读取文件的所有比特
        byte[] fileBytes = Files.readAllBytes(Paths.get(path));
        // 摘要更新
        digest.update(fileBytes);
        //完成哈希摘要计算并返回特征值
        byte[] digested = digest.digest();
        // 进行十六进制的输出
        return HexUtils.toHexString(digested);
    }


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

    public static MultipartFile File2MultipartFile(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",file.getName(),"text/plain", IOUtils.toByteArray(input));
        return multipartFile;
    }

    public static File MultipartFile2File(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
//        org.apache.commons.io.FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
        return file;
    }
}
