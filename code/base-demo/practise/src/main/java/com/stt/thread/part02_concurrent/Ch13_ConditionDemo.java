package com.stt.thread.part02_concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ttshe2 on 2019/6/5.
 */
public class Ch13_ConditionDemo {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try{
            // 当调用await后，当前线程会释放锁在此等待
            condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal(){
        lock.lock();
        try{
            // 其他线程调用signal方法，通知当前线程从await返回
            // 注意：在返回前已经获取得到锁
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

}
