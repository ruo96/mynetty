package com.wrh.thread.AQS;

import com.wrh.reflection.S;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wuruohong
 * @Classname NonReentrantLock
 * @Description TODO
 * @Date 2021/4/28 17:53
 */
public class NonReentrantLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean isHeldExclusively(){
            return super.getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (arg != 1) {
                throw new RuntimeException("arg not is 1");
            }
            if (compareAndSetState(1, arg)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (arg != 0) {
                throw new RuntimeException("arg not 1");
            }
            setExclusiveOwnerThread(null);
            setState(arg);
            return true;
        }

        public ConditionObject createConditionObject() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.createConditionObject();
    }
}
