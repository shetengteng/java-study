package com.stt.thread.part01_base;

import java.util.concurrent.TimeUnit;

/**
 * 使用 jstack工具查看线程的状态
 * Created by Administrator on 2019/5/24.
 */
public class Ch07_ThreadStateDemo {
	/**
	 * 线程进行睡眠
	 */

	static class TimeWaiting implements Runnable{
		@Override
		public void run() {
			for(;;) {sleep(10);}
		}
	}

	/**
	 * 线程在等待
	 */
	static class Waiting implements Runnable{
		@Override
		public void run() {
			for(;;){
				synchronized (Waiting.class){
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 阻塞测试
	 */
	static class Blocked implements Runnable{
		@Override
		public void run() {
			synchronized (Blocked.class){
				for(;;){
					sleep(10);
				}
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"timeWaiting").start();
		new Thread(new Waiting(),"Waiting").start();
		// 使用2个线程获取锁，一个获取锁成功，一个阻塞
		new Thread(new Blocked(),"blocked-1").start();
		new Thread(new Blocked(),"blocked-2").start();
	}

	private static void sleep(long time){
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
