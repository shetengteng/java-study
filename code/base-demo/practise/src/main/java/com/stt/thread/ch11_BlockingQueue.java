package com.stt.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ch11_BlockingQueue {

	public static void main(String[] args) {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		// 如下操作不满足条件会抛出异常
		System.out.println(queue.offer("1"));
		System.out.println(queue.offer("2"));
		System.out.println(queue.offer("3"));

		System.out.println(queue.offer("4"));

		// 获取头部值，不移除
		System.out.println(queue.peek());

		// 获取头部值，并移除
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}