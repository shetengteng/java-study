package com.stt.thread.part01_base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于解释内存语义
 * Created by Administrator on 2019/5/23.
 */
public class Ch05_ReentrantLockDemo {

	int a = 0;
	// 默认使用非公平锁
	ReentrantLock lock = new ReentrantLock();
	public void read(){
		lock.lock();
		try{
			int i = a;
			System.out.println(i);
		}finally {
			lock.unlock();
		}
	}

	public void write(){
		lock.lock();
		try{
			a ++;
		}finally {
			lock.unlock();
		}
	}

}
