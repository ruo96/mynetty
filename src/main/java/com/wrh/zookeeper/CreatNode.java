package com.wrh.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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
@Slf4j
public class CreatNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static final String address = "172.18.90.23:2181";
    public static final int sessionTimeOunt = 50000;

    private static final String pathRoot = "/";
    private static final String path = "/zoo";
    private static final String path1 = "/zoo0000000014";

    public static Stat stat = new Stat();



    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(address,sessionTimeOunt,new CreatNode());
        System.out.println("当前的zookeeper状态:　"+ zooKeeper.getState());

        try {
            System.out.println("开始等待");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper状态已经建立");

        /*创建节点*/
//        String node1 = zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        /*显示子节点*/
        List<String> list = zooKeeper.getChildren(pathRoot,true);
        log.info("子节点为: {}",list);


        /*删除子节点*/
//        zooKeeper.delete(path1,-1);
        log.info("新的子节点为:{}", zooKeeper.getChildren(pathRoot,true));

        /*stat = zooKeeper.setData(path,"wrh".getBytes(),-1);
        log.info("更新后的状态:　{}",stat.getVersion());

        stat = zooKeeper.setData(path,"rjj".getBytes(),-1);
        log.info("更新后的状态:　{}",stat.getVersion());*/

        stat = zooKeeper.exists(path,true);
        log.info("节点{} 是否存在:{}",path,stat);






    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("获取观察事件:" + watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
