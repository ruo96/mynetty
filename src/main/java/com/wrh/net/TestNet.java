package com.wrh.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description: mynetty
 * Created by wuruohong on 2020/1/8 14:33
 */
@Slf4j
public class TestNet {

    @Test
    public void Test() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        log.info(">>> hostAddress: {}",hostAddress);  // hostAddress: 10.23.50.62

        log.info(">>> timestamp: {}", System.currentTimeMillis());
    }
}
