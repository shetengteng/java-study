package com.stt.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ch12_BlockingQueue {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		// 如下操作不满足条件会抛出异常
		queue.put("1");
		queue.put("2");
		queue.put("3");
//		System.out.println("=======阻塞1======");
//		queue.put("4");

		queue.take();
		queue.take();
		queue.take();
		System.out.println("=======阻塞2======");
		queue.take();

	}
}