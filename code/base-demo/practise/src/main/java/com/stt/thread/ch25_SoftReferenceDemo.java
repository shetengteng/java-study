package com.stt.thread;

import java.lang.ref.SoftReference;

/**
 * 内存充足时，不回收
 * 内存不够时，进行回收
 * 配置内存 -Xms2m -Xmx2m -XX:+PrintGCDetails
 */
public class ch25_SoftReferenceDemo {

	public static void MemoryEnough() {
		Object o1 = new Object();
		SoftReference<Object> softReference = new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());

		o1 = null;
		System.gc();

		System.out.println(o1); // null
		System.out.println(softReference.get()); // 可以获取
	}


	public static void MemoryNotEnough() {
		Object o1 = new Object();
		SoftReference<Object> softReference = new SoftReference<Object>(o1);
		System.out.println(o1);
		System.out.println(softReference.get());

		o1 = null;

		try {
			byte[] b = new byte[1 * 512 * 1024]; // 直接申请大对象，到老年代，触发gc
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(o1); // null
			System.out.println(softReference.get()); // null
		}
	}

	public static void main(String[] args) {
//		MemoryEnough();
		MemoryNotEnough();
	}
}
