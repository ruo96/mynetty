package com.wrh.resource;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

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
}
