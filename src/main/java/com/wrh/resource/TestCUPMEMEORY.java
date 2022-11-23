package com.wrh.resource;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/7 17:51
 */
public class TestCUPMEMEORY {

    private static final int memoryGUnit = 1024 * 1024 * 1024;

    @Test
    public void Test11() {
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Map<String, Object> map = new HashMap<>();
        map.put("cpu", Runtime.getRuntime().availableProcessors());
        map.put("freeMemory", osmb.getFreePhysicalMemorySize()/memoryGUnit);
        map.put("totalMemory", osmb.getTotalPhysicalMemorySize()/memoryGUnit);
        System.out.println("map = " + map);
    }

    @Test
    public void Test30() throws IOException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
//        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "com/wrh/resource/component"+"/"+"**/*.class";
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "../*.yml";
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                System.out.println("resource.getFilename() = " + resource.getFilename());
            }
        }

    }

    @Test
    public void Test49() throws IOException {
//        Resource[] resources = TestCUPMEMEORY.class.getClassLoader().getResources("*.*");

        String path = TestCUPMEMEORY.class.getClassLoader().getResource("").getPath();
        System.out.println("path = " + path);

        /**
         * 1、getResource("")不带"/“时候是从当前类所在包路径去获取资源
         * 2、getResource("/")带”/"时候是从classpath的根路径获取
         */
        System.out.println("TestCUPMEMEORY.class.getResource(\"\") = " + TestCUPMEMEORY.class.getResource(""));
        System.out.println("TestCUPMEMEORY.class.getResource(\"/\") = " + TestCUPMEMEORY.class.getResource("/"));


    }
}
