package com.wrh.file.yasuo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:00 2019/12/11 0011
 * @Modified By:
 */
@Slf4j
public class TestZip {

    public static final String ZIP_FILE = "e:\\file\\1.zip";
    public static final String JPG_FILE = "e:\\file\\1.jpg";

    public static void main(String[] args) {
        File zipFile = new File(ZIP_FILE);

        try(ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))) {
            long beginTime = System.currentTimeMillis();
                try(InputStream input = new FileInputStream(JPG_FILE)){
//                    zipOut.putNextEntry(new ZipEntry());
                }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 压缩单个文件*/
    @SneakyThrows
    @Test
    public void Test38() {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setCompressionLevel(CompressionLevel.HIGHER);  // 压缩级别  ultra 默认是normal
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);  // aes加密， 如果要使用zip标准加密， 使用ZIP_STANDARD替换AES

        ZipFile zipFile = new ZipFile("E:\\data.zip", "password".toCharArray());

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("data.txt");
        File file = new File("E:\\data3.txt");
        inputStream2File(in, file);

        zipFile.addFile(file, zipParameters);

    }

    /** 压缩多个文件*/
    @SneakyThrows
    @Test
    public void Test64() {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        List<File> fileList = Arrays.asList(
                new File("e:\\data1.txt"),
                new File("e:\\data2.txt")
        );
        ZipFile zipFile = new ZipFile("E:\\data.zip", "password".toCharArray());
        zipFile.addFiles(fileList, zipParameters);
    }

    /** 压缩目录*/
    @SneakyThrows
    @Test
    public void Test84() {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        ZipFile zipFile = new ZipFile("E:\\data.zip", "password".toCharArray());
        zipFile.addFolder(new File("E:\\data"), zipParameters);

        /**
         * 创建一个分割的压缩文件
         * 我们可以通过使用createSplitZipFile和createSplitZipFileFromFolder方法，``将压缩文件分割成几个文件。
         *
         * ZipFile zipFile = new ZipFile("compressed.zip", "password".toCharArray());
         * int splitLength = 1024 * 1024 * 10; //10MB
         * zipFile.createSplitZipFile(Arrays.asList(new File("aFile.txt")), zipParameters, true, splitLength);
         * zipFile.createSplitZipFileFromFolder(new File("/users/folder_to_add"), zipParameters, true, splitLength);
         * splitLength的单位是字节。
         */
    }

    /** 提取所有 单个文件*/
    @SneakyThrows
    @Test
    public void Test105() {
        ZipFile zipFile = new ZipFile("data.zip", "password".toCharArray());
        zipFile.extractAll("/destination_directory");
        zipFile.extractFile("aFile.txt", "/destination_directory");
    }

    /**
     * 文件流写入file
     * @param destination
     * @param input
     * @throws IOException
     */
    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();

    }

    /**
     * 将inputStream转化为file
     * @param is
     * @param file 要输出的文件目录
     */
    public static void inputStream2File (InputStream is, File file) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[8192];

            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } finally {
            os.close();
            is.close();
        }
    }

    @SneakyThrows
    @Test
    public void Test82() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("data.txt");
//        writeToLocal("e:\\data1.txt", in);
        File file = new File("E:\\data2.txt");
        inputStream2File(in, file);

    }
}
