package com.stt.thread;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ch29_PhantomReferenceDemo {
	public static void main(String[] args) throws InterruptedException {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		PhantomReference<Object> ref = new PhantomReference<>(o1,referenceQueue);

		System.out.println(o1);
		System.out.println(ref.get());
		System.out.println(referenceQueue.poll());

		o1=null;
		System.gc();
		Thread.sleep(500);
		System.out.println("--------------");

		System.out.println(o1);
		System.out.println(ref.get());
		System.out.println(referenceQueue.poll());
	}
}