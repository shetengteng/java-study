package com.stt.thread.part01_base;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 * Created by Administrator on 2019/4/28.
 */
public class Ch01_DeadLockDemo {

	private static final String lock1 = "lock1";
	private static final String lock2 = "lock2";

	public static void main(String[] args) {
		// 线程1
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock1){
					System.out.println("t1 obtain lock1");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lock2){
						System.out.println("t1 obtain lock2");
					}
				}
			}
		});

		// 线程2
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock2){
					System.out.println("t2 obtain lock2");
					synchronized (lock1){
						System.out.println("t2 obtain lock1");
					}
				}
			}
		});
		t1.start();
		t2.start();

	}

}
