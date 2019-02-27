package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/27 0027
 * @Modified By:
 */
public class FluentExample {

    private static String path = "/flu1";
    private static String path1 = "/flu1/1";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

//        流式表达
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CreatNode.address)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        /*client.create().forPath(path);
        client.create().forPath(path,"init".getBytes());*/

//        client.create().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path1);

        client.delete().deletingChildrenIfNeeded().forPath(path);

        client.delete().guaranteed().forPath(path);
        TimeUnit.SECONDS.sleep(50);
    }


}
