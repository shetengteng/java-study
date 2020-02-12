package com.stt.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ch09_CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(6,()->{
			System.out.println("全部完成");
		});

		for (int i = 0; i < 6; i++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+" 执行完成");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 阻塞解除...");
			},"t"+i).start();
		}
	}
}
