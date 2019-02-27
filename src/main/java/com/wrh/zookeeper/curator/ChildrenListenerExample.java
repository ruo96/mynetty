package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:24 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class ChildrenListenerExample {
    private static String path = "/asyn1";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
                                    .connectString(CreatNode.address)
                                    .sessionTimeoutMs(5000)
                                    .retryPolicy(new ExponentialBackoffRetry(1000,3))
                                    .build();

    public static void main(String[] args) throws Exception {

        client.start();

        PathChildrenCache cache = new PathChildrenCache(client,path,true);

        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {

            }
        });

        client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        TimeUnit.SECONDS.sleep(1);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
        TimeUnit.SECONDS.sleep(1);

        client.delete().forPath(path + "/c1");
        TimeUnit.SECONDS.sleep(1);

        client.delete().forPath(path);
        TimeUnit.SECONDS.sleep(50);
    }


}
