package com.wrh.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:55 2019/2/26 0026
 * @Modified By:
 */
public class MyWatch implements Watcher {

    private CountDownLatch latch;
    public MyWatch(CountDownLatch latch) {
        this.latch=latch;
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()){
            latch.countDown();
        }
    }

}
