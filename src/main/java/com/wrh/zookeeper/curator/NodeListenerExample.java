package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
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
public class NodeListenerExample {
    private static String path = "/asyn";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
                                    .connectString(CreatNode.address)
                                    .sessionTimeoutMs(5000)
                                    .retryPolicy(new ExponentialBackoffRetry(1000,3))
                                    .build();

    static CountDownLatch semaphore = new CountDownLatch(2);


    public static void main(String[] args) throws Exception {
        client.start();



        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());

        final NodeCache cache = new NodeCache(client,path,false);
        cache.start();

        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if( null != cache.getCurrentData()){
                    log.info("数据有改变, 新的数据:{}" , new String(cache.getCurrentData().getData()));
                }else {
                    log.info("数据已经为空了!");
                }

            }
        });

        client.setData().forPath(path,"u".getBytes());
        TimeUnit.SECONDS.sleep(1);
        client.setData().forPath(path,"v".getBytes());
        TimeUnit.SECONDS.sleep(1);
        client.delete().deletingChildrenIfNeeded().forPath(path);
        TimeUnit.SECONDS.sleep(50);
    }


}
