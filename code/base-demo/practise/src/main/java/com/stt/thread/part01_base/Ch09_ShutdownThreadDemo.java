package com.stt.thread.part01_base;

import java.util.concurrent.TimeUnit;

/**
 * 安全的终止线程
 * Created by Administrator on 2019/5/26.
 */
public class Ch09_ShutdownThreadDemo {

	static class Runner implements Runnable{
		long count = 0;
		volatile boolean on = true;
		public void cancel(){
			on = false;
		}

		@Override
		public void run() {
			while(on && !Thread.currentThread().isInterrupted()){
				count ++;
			}
			System.out.println(Thread.currentThread().getName()+" count: "+count);
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Runner one = new Runner();
		Thread oneThread = new Thread(one,"one");
		oneThread.start();
		TimeUnit.SECONDS.sleep(2);
		// 使用 volatile变量关闭
		one.cancel();

		Runner two = new Runner();
		Thread twoThread = new Thread(two,"two");
		twoThread.start();
		TimeUnit.SECONDS.sleep(2);
		// 使用中断关闭
		twoThread.interrupt();
	}

}
