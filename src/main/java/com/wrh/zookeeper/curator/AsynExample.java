package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:24 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class AsynExample {
    private static String path = "/asyn";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
                                    .connectString(CreatNode.address)
                                    .sessionTimeoutMs(5000)
                                    .retryPolicy(new ExponentialBackoffRetry(1000,3))
                                    .build();

    static CountDownLatch semaphore = new CountDownLatch(2);

    static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        client.start();
        log.info("主线程: {}",Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                log.info("事件返回结果码:　{}  事件类型:{}",curatorEvent.getResultCode(),curatorEvent.getType());
                log.info("当前线程名称:{}",Thread.currentThread().getName());
                semaphore.countDown();
            }
        },tp).forPath(path,"init".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                log.info("事件返回结果码:　{}  事件类型:{}",curatorEvent.getResultCode(),curatorEvent.getType());
                log.info("当前线程名称:{}",Thread.currentThread().getName());
                semaphore.countDown();
            }
        }).forPath(path,"init".getBytes());

        semaphore.await();
        tp.shutdown();
    }


}
