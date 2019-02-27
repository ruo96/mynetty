package com.wrh.zookeeper.zkclient;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:49 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class CreatSession {

    private static final String path = "/zknode/z1";
    private static final String path1 = "/zknode";

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(CreatNode.address,CreatNode.sessionTimeOunt);
        log.info("session established!");

        Map<String,String> map = new HashMap<>();
//        zkClient.create(path,map, CreateMode.EPHEMERAL);

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                log.info("==============父路径: {} 当前子节点: {}", parentPath,currentChilds);

            }
        });

        log.info("开始创建节点 /zknode/z1");
        zkClient.createPersistent(path);

        log.info("创建结束, 开始等待监听事件,sleep...");
        TimeUnit.SECONDS.sleep(1);
        zkClient.getChildren(path);
        log.info("开始创建节点 /zknode/z1/c1");

        zkClient.createPersistent(path+"/c1");
        TimeUnit.SECONDS.sleep(1);
        log.info("开始删除节点 /zknode/z1/c1");

        zkClient.delete(path + "/c1");
        TimeUnit.SECONDS.sleep(100);

        log.info("开始删除节点 /zknode/z1");

        zkClient.delete(path);

//        递归删除节点以及其下的子节点
//        zkClient.deleteRecursive(path1);
        List<String> list = zkClient.getChildren(path1);
        log.info("===list:{}",list);
    }
}
