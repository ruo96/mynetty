package com.wrh.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:00 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class ACL implements Watcher {

    private final String path = "/zoo";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);

        zooKeeper.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper.create("/zoo1","init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
//        Thread.sleep(Integer.MAX_VALUE);
        Thread.sleep(2000L);

        log.info("创建者获取数据:{}",new String(zooKeeper.getData("/zoo1",true,CreatNode.stat)));

        ZooKeeper zooKeeper2 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);

        zooKeeper2.addAuthInfo("digest","foo:true".getBytes());
        //提示没有权限
        log.info("另外一个zk获取数据:{}",new String(zooKeeper2.getData("/zoo1",true,CreatNode.stat)));
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }


}
