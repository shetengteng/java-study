package com.stt.thread;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 弱引用，垃圾回收机制回收该对象占用的内存
 */
public class ch26_WeakReferenceDemo {

	public static void main(String[] args) {
		Object o1 = new Object();
		WeakReference<Object> ref = new WeakReference(o1);
		System.out.println(o1);
		System.out.println(ref.get());

		o1 = null;
		System.gc();

		System.out.println(o1); // null
		System.out.println(ref.get()); // null
	}
}
