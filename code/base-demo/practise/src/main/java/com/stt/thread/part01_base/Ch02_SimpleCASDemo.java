package com.stt.thread.part01_base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环调用cas方法
 */
public class Ch02_SimpleCASDemo{

	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private int i = 0;

	public void safeCount(){
		for(;;){
			// 此时获取最新的数值，对该数值进行更新
			int k = atomicInteger.get();
			// 进行cas操作，放入旧值和新值，乐观锁
			// 不断循环的调用
			boolean success = atomicInteger.compareAndSet(k,++k);
			if(success){
				break;
			}
		}
	}

	public void count(){
		i++;
	}

	public static void main(String[] args) {

		final Ch02_SimpleCASDemo cas = new Ch02_SimpleCASDemo();
		// 600个线程
		List<Thread> ts = new ArrayList<>(100);
		long start = System.currentTimeMillis();
		for(int j=0 ; j<100 ; j++){
			Thread t = new Thread(()->{
				for(int i=0;i<1000;i++){
					cas.safeCount();
					cas.count();
				}
			});
			ts.add(t);
		}
		for(Thread t:ts){
			t.start();
		}
		for(Thread t:ts){
			try {
				// 判断t线程是否结束，结束则继续，否则main进行阻塞
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(cas.i);
		System.out.println(cas.atomicInteger.get());
		System.out.println(System.currentTimeMillis() - start);
	}
}
