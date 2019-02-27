package com.wrh.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:05 2019/2/26 0026
 * @Modified By:
 */

public class GetNodeAsyn implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static final String address = "172.18.90.23:2181";
    private static final int sessionTimeOunt = 10000;

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper(address,sessionTimeOunt,new GetNodeAsyn());

        System.out.println("当前的zookeeper状态:　"+ zk.getState());

        try {
            System.out.println("开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper状态已经建立");

        List<String> childrenList = zk.getChildren("/myzoo",true);

        System.out.println(childrenList);
        TimeUnit.SECONDS.sleep(100);

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            System.out.println(" 连接建立 ");
            if(Event.EventType.None != watchedEvent.getType() && null != watchedEvent.getPath()){
                countDownLatch.countDown();
                System.out.println("执行countdown");
            }else if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("重新获取子节点: " + zk.getChildren(watchedEvent.getPath(),true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
