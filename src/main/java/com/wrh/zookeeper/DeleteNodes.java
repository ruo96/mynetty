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
public class DeleteNodes implements Watcher {

    private static final String path1 = "/zoo3";
    private static final String path2 = "/zoo3/child";
    private static final String path3 = "/zoo3/child/1";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper1 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);

        zooKeeper1.addAuthInfo("digest","foo:true".getBytes());
//        zooKeeper1.create(path1,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper1.create(path2,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper1.create(path3,"init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
//        Thread.sleep(Integer.MAX_VALUE);
        Thread.sleep(2000L);
        ZooKeeper zooKeeper2 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);
        try{

            zooKeeper2.delete(path2,-1);
        }catch (Exception e){
            log.info("删除子节点{}失败,{}",path2, e);
        }

        ZooKeeper zooKeeper5 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);
        zooKeeper5.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper5.delete(path3,-1);

        ZooKeeper zooKeeper4 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);
        zooKeeper4.addAuthInfo("digest","foo:true".getBytes());
        zooKeeper4.delete(path2,-1);


        ZooKeeper zooKeeper3 = new ZooKeeper(CreatNode.address,CreatNode.sessionTimeOunt,null);
        zooKeeper3.delete(path1,-1);
        log.info("成功删除节点{}", path1);


    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }


}
