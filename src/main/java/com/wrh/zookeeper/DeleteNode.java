package com.wrh.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:05 2019/2/26 0026
 * @Modified By:
 */

public class DeleteNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final String address = "172.18.90.23:2181";
    private static final int sessionTimeOunt = 50000;



    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(address,sessionTimeOunt,new DeleteNode());
        System.out.println("当前的zookeeper状态:　"+ zooKeeper.getState());

        try {
            System.out.println("开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper状态已经建立");

        zooKeeper.delete("/myzoo1",-1);

        List<String> lists = zooKeeper.getChildren("/",true);
        System.out.println("目前的节点:" + lists);




    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("获取观察事件:" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
