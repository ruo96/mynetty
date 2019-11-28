package com.wrh.property;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:43 2019/11/11 0011
 * @Modified By:
 */
public class TestReadFromPropertiesFile {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = TestReadFromPropertiesFile.class.getClassLoader().getResourceAsStream("custom.properties");

        properties.load(new InputStreamReader(inputStream,"utf-8"));
//        properties.load(new InputStreamReader(inputStream,"gbk"));

        properties.list(System.out);

    }
}
