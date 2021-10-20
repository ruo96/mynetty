package com.wrh.property;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:56 2019/11/11 0011
 * @Modified By:
 *
 * 从源码上可以看出，Properties 继承自 Hashtable，大部分方法都复用于 Hashtable，与 Hashtable 不同的是， Properties 中的 key 和 value 都是字符串。
 *
 * 实际开发中，Properties 主要用于读取配置文件，尤其是在不同的环境下，变量值需要不一样的情况，可以通过读取配置文件来避免将变量值写死在 java 的枚举类中，以达到一行代码，多处运行的目的！
 *
 * 在读取 Properties 配置文件的时候，容易因文件路径找不到报错，可以参考 properties 文件加载的几种方式，如果网友还有新的加载方法，欢迎给我们留言！
 *
 */
@Slf4j
public class TestLoadMethod {

    /**
     *
     */
    @Test
    public void test() throws IOException {
        Properties prop = new Properties();
        //获取文件绝对路径
        String filePath = "D:\\0 pro\\mynetty\\src\\main\\resources\\custom.properties";
//        String filePath = "custom.properties";
        //加载配置文件
        InputStream in = new FileInputStream(new File(filePath));
        //读取配置文件
        prop.load(new InputStreamReader(in, "UTF-8"));
        System.out.println("userName："+prop.getProperty("userName"));
    }

    @Test
    public void test1() throws IOException {
        Properties prop = new Properties();
        InputStream in = TestLoadMethod.class.getClassLoader().getResourceAsStream("custom.properties");

        prop.load(new InputStreamReader(in,"utf-8"));

        log.info("username: {}", prop.getProperty("userName"));
    }

    /**
     * 使用ClassLoader类的getSystemResourceAsStream方法获取
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        Properties prop = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("custom.properties");

        prop.load(new InputStreamReader(in, "utf-8"));
        log.info("username: {}", prop.getProperty("userName"));

    }

    /**
     * ResourceBundle 类加载文件，与 Properties 有所不同，ResourceBundle 获取 properties 文件不需要加.properties后缀名，只需要文件名即可。
     *
     * ResourceBundle 是按照iso8859编码格式来读取原属性文件，如果是读取中文内容，需要进行转码处理。
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        ResourceBundle resource = ResourceBundle.getBundle("custom");

        String value = new String(resource.getString("userName").getBytes("ISO-8859-1"),"UTF-8");
        log.info("username: {}", value);
    }

    @Test
    public void Test81() throws IOException {
        Properties properties = new Properties();
        Properties properties2 = new Properties();
//        FileInputStream fis = new FileInputStream("src/main/resources/custom.properties");
        InputStream fis = Object.class.getResourceAsStream("/custom.properties");
        InputStream fis2 = TestLoadMethod.class.getClassLoader().getResourceAsStream("custom.properties");
//        InputStream fis = Object.class.getClassLoader().getResourceAsStream("custom.properties");
        properties.load(fis);
        properties2.load(fis2);
        String name  = properties.getProperty("userName");
        String name2  = properties2.getProperty("userName");
        System.out.println("name = " + name);
        System.out.println("name2 = " + name2);

       /* File file = new File(this.getClass().getResource("custom.properties").getPath());
        System.out.println("file = " + file);*/


    }
}
