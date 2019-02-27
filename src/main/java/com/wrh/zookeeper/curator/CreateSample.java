package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:19 2019/2/27 0027
 * @Modified By:
 */
public class CreateSample {
    public static void main(String[] args) throws InterruptedException {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(CreatNode.address,5000,3000,retryPolicy);

        client.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
