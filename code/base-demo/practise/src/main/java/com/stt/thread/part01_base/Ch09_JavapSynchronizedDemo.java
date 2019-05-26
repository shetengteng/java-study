package com.stt.thread.part01_base;

/**
 * 使用javap分析Synchronized实现
 * Created by Administrator on 2019/5/26.
 */
public class Ch09_JavapSynchronizedDemo {

	public static void main(String[] args) {
		synchronized (Ch09_JavapSynchronizedDemo.class){
			System.out.println("main");
		}
		test();
	}

	public static synchronized void test(){
		System.out.println("test");
	}

}
