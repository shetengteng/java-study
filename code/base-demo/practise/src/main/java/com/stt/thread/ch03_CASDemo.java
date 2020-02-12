package com.stt.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ch03_CASDemo {

	public static void main(String[] args) {

		AtomicInteger n = new AtomicInteger(2);

		System.out.println(n.compareAndSet(2,3)+" current:"+n.get());

		System.out.println(n.compareAndSet(2,4)+" current:"+n.get());

		n.getAndIncrement();
	}
}