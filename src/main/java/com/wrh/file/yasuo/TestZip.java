package com.wrh.file.yasuo;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
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
}
