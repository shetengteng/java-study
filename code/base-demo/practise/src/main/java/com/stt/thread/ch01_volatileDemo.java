package com.stt.thread;

import java.util.concurrent.TimeUnit;

/**
 * 可见性代码
 */
public class ch01_volatileDemo {

	public static void main(String[] args) {

		MyData d = new MyData();
		// 线程1开启
		new Thread(()->{

			System.out.println(Thread.currentThread().getName()+" start");

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			d.change();
			System.out.println(Thread.currentThread().getName()+" end");

		},"thread01").start();

		// 主线程执行
		while(d.num == 0){
			// 一直阻塞
		}
		System.out.println("finish");
	}
}

class MyData{
	volatile int num = 0;
	public void change(){
		this.num = 1;
	}
}