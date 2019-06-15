package com.stt.thread.part02_concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Ch14_BoundedQueueByConditionDemo {

    // 一个环形的阻塞队列
    static class BoundedQueue<T>{
        private Object[] items;
        private int addIndex;
        private int removeIndex;
        // 用于边界条件判断是否满了，或者空了
        private int count;
        private Lock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();

        public BoundedQueue(int size){
            items = new Object[size];
        }

        public void add(T t) throws InterruptedException {
            lock.lock();
            try{
                // 判断是否可以添加
                // 如果满了，当前线程进入等待队列
                // 这里使用while，表示唤醒后需要再次判断
                while(count == items.length){
                    notFull.await();
                }
                items[addIndex] = t;
                addIndex ++;
                if(addIndex == items.length){
                    addIndex = 0;
                }
                count ++;
                // 唤醒等待队列中的remove的线程
                notEmpty.signal();
            }finally {
                lock.unlock();
            }
        }

        public T remove() throws InterruptedException {
            lock.lock();
            try{
                while(count == 0){
                    notEmpty.await();
                }
                Object t = items[removeIndex];
                // 使用下标进行标识删除的位置，类似于一个环形队列
                // 从头部开始删除
                removeIndex ++;
                if(removeIndex == items.length){
                    removeIndex = 0;
                }
                count --;
                // 唤醒等待的add的线程
                notFull.signal();
                return (T) t;
            }finally {
                lock.unlock();
            }
        }

    }

}
