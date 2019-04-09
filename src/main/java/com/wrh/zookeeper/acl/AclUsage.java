package com.wrh.zookeeper.acl;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:05 2019/2/28 0028
 * @Modified By:
 */
public class AclUsage {

    int i = 1;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(DigestAuthenticationProvider.generateDigest("foo:zk-book"));
    }
}
