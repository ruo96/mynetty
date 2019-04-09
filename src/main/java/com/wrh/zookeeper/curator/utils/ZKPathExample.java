package com.wrh.zookeeper.curator.utils;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.ZooKeeper;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:19 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class ZKPathExample {

    static final String path = "/utils";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString(CreatNode.address)
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(3000,3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();

        ZooKeeper zooKeeper = client.getZookeeperClient().getZooKeeper();
        log.info("fixForNamespace  {}",ZKPaths.fixForNamespace(path,"/sub"));
        log.info("makePath  {}",ZKPaths.makePath(path,"/sub"));

        log.info(ZKPaths.getNodeFromPath(path+"/sub"));

        ZKPaths.PathAndNode pn = ZKPaths.getPathAndNode(path+"/sub1");
        log.info("path,{}",pn.getPath());
        log.info("node,{}",pn.getNode());

        String dir1 = path + "/child1";
        String dir2 = path + "/child2";
        ZKPaths.mkdirs(zooKeeper,dir1);
        ZKPaths.mkdirs(zooKeeper,dir2);

        log.info("{}",ZKPaths.getSortedChildren(zooKeeper,path));
        ZKPaths.deleteChildren(client.getZookeeperClient().getZooKeeper(),path,true);
    }
}
