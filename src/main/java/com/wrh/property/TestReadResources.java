package com.wrh.property;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URLDecoder;

/**
 * @author : wuruohong
 * @description :
 * 获取 /resources 目录资源文件的 9 种方法
 * 项目开发中，经常会有一些静态资源，被放置在resources目录下，随项目打包在一起，代码中要使用的时候，通过文件读取的方式，加载并使用
 *
 *
 * @Date : 2022/9/29 16:14
 */
public class TestReadResources {

    /**
     * 根据文件路径读取文件内容
     *
     * @param fileInPath
     * @throws IOException
     */
    public static void getFileContent(Object fileInPath) throws IOException {
        BufferedReader br = null;
        if (fileInPath == null) {
            return;
        }
        if (fileInPath instanceof String) {
            br = new BufferedReader(new FileReader(new File((String) fileInPath)));
        } else if (fileInPath instanceof InputStream) {
            br = new BufferedReader(new InputStreamReader((InputStream) fileInPath));
        }
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

    /** 方式一
     主要核心方法是使用getResource和getPath方法，这里的getResource("")里面是空字符串*/
    public void function1(String fileName) throws IOException {
        String path = this.getClass().getClassLoader().getResource("").getPath();//注意getResource("")里面是空字符串
        System.out.println(path);
        String filePath = path + fileName;
        System.out.println(filePath);
        getFileContent(filePath);
    }

    /**方式2
     * 直接通过文件名getPath来获取路径
     * 主要核心方法是使用getResource和getPath方法，直接通过getResource(fileName)方法获取文件路径，注意如果是路径中带有中文一定要使用URLDecoder.decode解码
     */
    public void function2(String fileName) throws IOException {
        String path = this.getClass().getClassLoader().getResource(fileName).getPath();//注意getResource("")里面是空字符串
        System.out.println(path);
        String filePath = URLDecoder.decode(path, "UTF-8");//如果路径中带有中文会被URLEncoder,因此这里需要解码
        System.out.println(filePath);
        getFileContent(filePath);
    }

    /**
     * 方式三
     * 直接通过文件名+getFile()来获取文件。如果是文件路径的话getFile和getPath效果是一样的，如果是URL路径的话getPath是带有参数的路径
     */
    public void function3(String fileName) throws IOException {
        String path = this.getClass().getClassLoader().getResource(fileName).getFile();//注意getResource("")里面是空字符串
        System.out.println(path);
        String filePath = URLDecoder.decode(path, "UTF-8");//如果路径中带有中文会被URLEncoder,因此这里需要解码
        System.out.println(filePath);
        getFileContent(filePath);
    }

    /**
     * 方式四（重要）
     * 直接使用getResourceAsStream方法获取流，上面的几种方式都需要获取文件路径，但是在SpringBoot中所有文件都在jar包中，没有一个实际的路径，因此可以使用以下方式。
     * springboot项目中需要使用此种方法，因为jar包中没有一个实际的路径存放文件
     */
    public void function4(String fileName) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        getFileContent(in);
    }

    /**
     * 方式五（重要）
     * 主要也是使用getResourceAsStream方法获取流，不使用getClassLoader可以使用getResourceAsStream("/配置测试.txt")
     * 直接从resources根路径下获取，SpringBoot中所有文件都在jar包中，没有一个实际的路径，因此可以使用以下方式。
     *
     */
    public void function5(String fileName) throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/" + fileName);
        getFileContent(in);
    }

    /**
     * 方式六（重要）
     * 通过ClassPathResource类获取文件流，SpringBoot中所有文件都在jar包中，没有一个实际的路径，因此可以使用以下方式
     * springboot项目中需要使用此种方法，因为jar包中没有一个实际的路径存放文件
     */
    public void function6(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        InputStream inputStream = classPathResource.getInputStream();
        getFileContent(inputStream);
    }

    /**
     * 方式七
     * 通过绝对路径获取项目中文件的位置，只是本地绝对路径，不能用于服务器获取
     */
    public void function7(String fileName) throws IOException {
        String rootPath = System.getProperty("user.dir");//E:\WorkSpace\Git\spring-framework-learning-example
        String filePath = rootPath + "\\chapter-2-springmvc-quickstart\\src\\main\\resources\\"+fileName;
        getFileContent(filePath);
    }

    /**
     * 方式八
     * 通过new File("")获取当前的绝对路径，只是本地绝对路径，不能用于服务器获取
     */
    public void function8(String fileName) throws IOException {
        File directory = new File("");
        String rootCanonicalPath = directory.getCanonicalPath();
        String rootAbsolutePath = directory.getAbsolutePath();
        System.out.println("rootCanonicalPath = " + rootCanonicalPath);
        System.out.println("rootAbsolutePath = " + rootAbsolutePath);
        String filePath = rootCanonicalPath + "\\mynetty\\src\\main\\";
        getFileContent(filePath);
    }

    /**
     * 方式九
     * 主要是通过设置环境变量，将文件放在环境变量中，原理也是通过绝对路径获取。
     * 示例中我设置了一个环境变量：TEST_ROOT=E:\\WorkSpace\\Git\\spring-framework-learning-example
     * System.getenv("TEST_ROOT");
     *  System.getProperty("TEST_ROOT")
     */
    public void function9(String fileName) throws IOException {
        System.setProperty("TEST_ROOT","E:\\WorkSpace\\Git\\spring-framework-learning-example");
        //参数为空
        String rootPath = System.getProperty("TEST_ROOT");
        System.out.println(rootPath);
        String filePath = rootPath + "\\chapter-2-springmvc-quickstart\\src\\main\\resources\\"+fileName;
        getFileContent(filePath);
    }


    @SneakyThrows
    public static void main(String[] args) {
        TestReadResources testReadResources = new TestReadResources();
//        testReadResources.function1("data.txt");
//        testReadResources.function2("data.txt");
//        testReadResources.function3("data.txt");
//        testReadResources.function4("data.txt");
//        testReadResources.function5("data.txt");
        testReadResources.function6("data.txt");
    }

}
