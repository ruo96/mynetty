package com.wrh.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:06 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class MasterSelect {
    private static final String path = "/master";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
                                        .connectString(path)
                                        .retryPolicy(new ExponentialBackoffRetry(3000,3))
                                        .sessionTimeoutMs(5000)
                                        .build();

    public static void main(String[] args) throws InterruptedException {
        client.start();

        LeaderSelector selector = new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                log.info("成为master角色");
                TimeUnit.SECONDS.sleep(3);
                log.info("完成master操作, 释放master权利");
            }
        });
        selector.autoRequeue();
        selector.start();
        TimeUnit.SECONDS.sleep(20);
    }
}
