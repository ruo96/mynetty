package com.wrh.ping;

import org.junit.Test;

import java.io.IOException;

/**
 * @author wuruohong
 * @Classname TestExec
 * @Description TODO
 * @Date 2021/2/3 16:31
 */
public class TestExec {
    @Test
    public void Test11() throws IOException {
        String pingCommand = "cmd";
        Runtime r = Runtime.getRuntime(); // 将要执行的ping命令,此命令是windows格式的命令
        Process p = r.exec(pingCommand);
    }
}
