package com.wrh.big.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Properties;

/**
 * @Classname TestHadoopPath
 * @Description TODO
 * @Date 2020/1/8 16:45
 * @Created by wuruohong
 */
@Slf4j
public class TestHadoopPath {

    @Test
    public void Test() {
        /*String home = System.getProperty("hadoop.home.dir");
        if (home == null) {
            home = System.getenv("HADOOP_HOME");
        }*/

        String javaHome = System.getenv("JAVA_HOME");
        String M2_HOME = System.getenv("M2_HOME");
        String NUMBER_OF_PROCESSORS = System.getenv("NUMBER_OF_PROCESSORS");
        String home = System.getenv("HADOOP_HOME");
        log.info(">>> javaHome {}",javaHome);
        log.info(">>> M2_HOME {}",M2_HOME);
        log.info(">>> NUMBER_OF_PROCESSORS {}",NUMBER_OF_PROCESSORS);
        log.info(">>> HADOOP_HOME {}",home);
    }

    @Test
    public void Test1() {
        //获取所有的属性
//        System.setProperty("hadoop.home.dir","aaa.bbb.ccc");

        Properties properties = System.getProperties();

        //遍历所有的属性
        for (String key : properties.stringPropertyNames()) {
            //输出对应的键和值

            if (key.equalsIgnoreCase("hadoop.home.dir")){
                System.out.println("YES");
                System.out.println(key + "=" + properties.getProperty(key));
                return ;
            }
        }

    }
}
