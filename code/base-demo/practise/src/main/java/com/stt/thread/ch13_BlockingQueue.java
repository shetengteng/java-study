package com.stt.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ch13_BlockingQueue {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);

		System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));
		System.out.println(queue.offer("a", 2L, TimeUnit.SECONDS));

	}
}