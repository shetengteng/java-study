package com.stt.thread.part02_concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的使用
 * Created by ttshe2 on 2019/6/4.
 */
public class Ch12_ReentrantReadWriteLockDemo {

    static class Cache{
        static Map<String,Object> map = new HashMap();
        static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        // 声明读锁
        static Lock r = rwl.readLock();
        // 声明写锁
        static Lock w = rwl.writeLock();

        public static final Object get(String key){
            r.lock();
            try{
                return map.get(key);
            }finally {
                r.unlock();
            }
        }

        public static final Object put(String key,Object value){
            w.lock();
            try{
                return map.put(key,value);
            }finally {
                w.unlock();
            }
        }

        public static final void clear(){
            w.lock();
            try{
                map.clear();
            }finally {
                w.unlock();
            }
        }
    }

}
