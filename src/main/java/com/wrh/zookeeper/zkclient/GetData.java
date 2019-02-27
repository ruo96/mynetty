package com.wrh.zookeeper.zkclient;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
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
public class GetData {

    private static final String path = "/zknode/z1";
    private static final String path1 = "/zknode";

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(CreatNode.address,CreatNode.sessionTimeOunt);
        log.info("session established!");

        Map<String,String> map = new HashMap<>();
        zkClient.create(path1,map, CreateMode.EPHEMERAL);

        zkClient.subscribeDataChanges(path1, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                log.info("node {} , new data {} ",s,o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                log.info(" node :{} delete!" ,s);
            }
        });

        log.info("当前节点数据:{}", java.util.Optional.ofNullable(zkClient.readData(path1)));
        zkClient.writeData(path1,"new data");
        TimeUnit.SECONDS.sleep(1);

        zkClient.delete(path1);
        log.info("/zknode 节点是否存在 :{}",zkClient.exists(path1));
    }
}
