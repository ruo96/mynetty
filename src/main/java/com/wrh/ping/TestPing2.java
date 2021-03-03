package com.wrh.ping;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author wuruohong
 * @Classname TestPing1
 * @Description TODO
 * @Date 2021/2/3 16:21
 */
public class TestPing2 {


    private static final Logger logger = LoggerFactory.getLogger(TestPing2.class);

    public static boolean ping(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        // 将要执行的ping命令,此命令是windows格式的命令
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        // Linux命令如下
        // String pingCommand = "ping" -c " + pingTimes + " -w " + timeOut + ipAddress;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug(pingCommand);
            }
            // 执行命令并获取输出
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int connectedCount = 0;
            String line;
            // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }
            // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真
            return connectedCount == pingTimes;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        }
    }
    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) { // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = compile("(\\d+ms)(\\s+)(TTL=\\d+)", CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ping(\"172.16.38.99\") = " + ping("172.16.38.99",10,3000));
    }


}
