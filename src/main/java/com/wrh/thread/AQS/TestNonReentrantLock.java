package com.wrh.thread.AQS;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestNonReentrantLock
 * @Description TODO
 * @Date 2021/4/28 18:00
 */
@Slf4j
public class TestNonReentrantLock {
    private static int j = 0;
    public static void main(String[] args) throws InterruptedException {
        NonReentrantLock  nonReentrantLock = new NonReentrantLock();

        Runnable runnable = () -> {
            //获取锁
            nonReentrantLock.lock();
            for (int i = 0; i < 100000; i++) {
                j++;
            }
            //释放锁
            nonReentrantLock.unlock();
        };

        Thread thread = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);

        thread.start();
        threadTwo.start();

        thread.join();
        threadTwo.join();

        log.info(">>> j is： {}",j);
    }

    @Test
    public void Test40() {

        /**
         * Inserts node into queue, initializing if necessary. See picture above.
         * @param node the node to insert
         * @return node's predecessor
         */
        /** AQS 同步队列的经典入队操作  如果没有就创建 有就添加*/

        /*private AbstractQueuedSynchronizer.Node enq(final AbstractQueuedSynchronizer.Node node) {
            for (;;) {
                AbstractQueuedSynchronizer.Node t = tail;
                if (t == null) { // Must initialize
                    if (compareAndSetHead(new AbstractQueuedSynchronizer.Node()))
                        tail = head;
                } else {
                    node.prev = t;
                    if (compareAndSetTail(t, node)) {
                        t.next = node;
                        return t;
                    }
                }
            }
        }*/

    }
}
