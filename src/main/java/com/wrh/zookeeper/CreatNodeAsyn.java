package com.wrh.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:05 2019/2/26 0026
 * @Modified By:
 */
@Slf4j
public class CreatNodeAsyn  {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final String address = "172.18.90.23:2181";
    private static final int sessionTimeOunt = 10000;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(address,sessionTimeOunt,new MyWatch(countDownLatch));
log.info("success");
        System.out.println("当前的zookeeper状态:　"+ zooKeeper.getState());

        try {
            System.out.println("开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper状态已经建立");

        zooKeeper.create("/myzoo1",
                "123".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),"I am context");

        TimeUnit.SECONDS.sleep(20000);

    }


}
