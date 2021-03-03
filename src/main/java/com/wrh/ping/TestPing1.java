package com.wrh.ping;

import java.net.InetAddress;

/**
 * @author wuruohong
 * @Classname TestPing1
 * @Description TODO
 * @Date 2021/2/3 16:21
 */
public class TestPing1 {
    public static boolean ping(String ipAddress) throws Exception {
        int  timeOut = 3000 ; //超时应该在3钞以上
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        // 当返回值是true时，说明host是可用的，false则不可。
        return status;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ping(\"172.16.38.99\") = " + ping("172.16.38.99"));
    }


}
