package com.stt.thread;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ch28_ReferenceQueueDemo {
	public static void main(String[] args) {
		Object o1 = new Object();
		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
		WeakReference<Object> ref = new WeakReference<>(o1,referenceQueue);

		System.out.println(o1);
		System.out.println(ref.get());
		System.out.println(referenceQueue.poll());

		o1=null;
		System.gc();
		System.out.println("--------------");

		System.out.println(o1);
		System.out.println(ref.get());
		System.out.println(referenceQueue.poll());
	}
}