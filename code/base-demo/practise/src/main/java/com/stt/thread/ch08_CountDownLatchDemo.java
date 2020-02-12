package com.stt.thread;

import java.util.concurrent.CountDownLatch;

public class ch08_CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 0; i < 6; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+" 执行完成");
				countDownLatch.countDown();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 继续执行");
			},"t"+i).start();
		}
		countDownLatch.await();
		System.out.println("全部完成");
	}
}
