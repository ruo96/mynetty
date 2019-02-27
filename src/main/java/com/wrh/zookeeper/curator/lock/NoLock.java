package com.wrh.zookeeper.curator.lock;

import lombok.extern.slf4j.Slf4j;

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
public class NoLock {

    public static void main(String[] args) {
        final CountDownLatch down = new CountDownLatch(1);

        for(int i =  0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    log.info("生成的订单号:{}" ,orderNo);
                }
            }).start();
            down.countDown();
        }
    }
}
