package com.wrh.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:05 2019/2/26 0026
 * @Modified By:
 */

public class TestZookeeper implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final String address = "127.0.0.1:2181";
    private static final int sessionTimeOunt = 5000;



    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(address,sessionTimeOunt,new TestZookeeper());
        System.out.println("当前的zookeeper状态:　"+ zooKeeper.getState());

        try {
            System.out.println("开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper状态已经建立");

        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();

//        ZooKeeper zooKeeper = new ZooKeeper(address,sessionTimeOunt,new TestZookeeper(),1L,"TEST".getBytes());

        zooKeeper = new ZooKeeper(address,sessionTimeOunt,new TestZookeeper(),sessionId,passwd);

        TimeUnit.SECONDS.sleep(5000);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("获取观察事件:" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
