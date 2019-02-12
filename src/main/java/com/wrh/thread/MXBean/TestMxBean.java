package com.wrh.thread.MXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:03 2019/2/12 0012
 * @Modified By:
 */
public class TestMxBean {

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);

        for(ThreadInfo threadInfo: threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName() + " ==== " + threadInfo.getThreadState());
        }
    }
}
