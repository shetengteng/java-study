package com.stt.thread.part01_base;

import java.util.concurrent.TimeUnit;

/**
 * 多个线程依次join，顺序执行
 * Created by Administrator on 2019/5/26.
 */
public class Ch11_JoinDemo {

	public static void main(String[] args) throws InterruptedException {
		// 开头的main线程
		Thread b = Thread.currentThread();
		for(int i=0;i<5;i++){
			Thread a = new Thread(new A(b),i+"");
			a.start();
			b = a;
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName()+" finish");
	}

	static class A implements Runnable{
		private Thread b;
		A(Thread b){
			this.b = b;
		}
		@Override
		public void run() {
			try {
				b.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" finish");
		}
	}
}
//结果
//main finish
//0 finish
//1 finish
//2 finish
//3 finish
//4 finish