package com.wrh.zookeeper.curator;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:32 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class GetDataExample {

    private static String path = "/data";
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);

//        流式表达
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(CreatNode.address)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();


        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
        byte[] data = client.getData().storingStatIn(stat).forPath(path);
        log.info("存入的数据: {}",new String(data));
        client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);

        TimeUnit.SECONDS.sleep(50);
    }


}
