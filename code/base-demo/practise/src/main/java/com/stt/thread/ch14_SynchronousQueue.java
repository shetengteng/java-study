package com.stt.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ch14_SynchronousQueue {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> queue = new SynchronousQueue<>();

		new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				try {
					System.out.println("produce: " + i);
					queue.put("" + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "p").start();

		for (int i = 0; i < 30; i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName()+" take:"+queue.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "c" + i).start();
			Thread.sleep(1000);
		}
	}
}