package com.stt.thread;

public class ch24_StrongReferenceDemo {
	public static void main(String[] args) {

		Object obj1 = new Object(); // 默认强引用
		Object obj2 = obj1; // 引用赋值
		obj1 = null; // 置空
		System.gc();
		System.out.println(obj2);
	}
}
