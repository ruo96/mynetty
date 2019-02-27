package com.wrh.zookeeper.curator.lock;

import com.wrh.zookeeper.CreatNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:35 2019/2/27 0027
 * @Modified By:
 */
@Slf4j
public class WithLock {

    static final String path = "/lock";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
                                    .connectString(CreatNode.address)
                                    .retryPolicy(new ExponentialBackoffRetry(3000,3))
                                    .sessionTimeoutMs(5000)
                                    .build();

    public static void main(String[] args) {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client,path);

        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 30 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                        lock.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    log.info("生成的订单号:{}",orderNo);
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        down.countDown();
    }
}
