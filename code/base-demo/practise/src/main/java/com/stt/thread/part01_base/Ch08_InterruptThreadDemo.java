package com.stt.thread.part01_base;

import java.util.concurrent.TimeUnit;

/**
 * 2个线程，一个始终睡眠，一个一直运行，都进行中断操作，判断2个线程的isInterrupted() 状态
 * Created by Administrator on 2019/5/26.
 */
public class Ch08_InterruptThreadDemo {

	static class SleepRunner implements Runnable{
		@Override
		public void run() {
			for(;;) {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class BusyRunner implements Runnable {
		@Override
		public void run() {
			for (;;){}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread sleep = new Thread(new SleepRunner(),"sleepRunner");
		sleep.setDaemon(true);
		Thread busy = new Thread(new BusyRunner(),"busyRunner");
		busy.setDaemon(true);
		sleep.start();
		busy.start();
		// 让sleep和busy充分运行
		TimeUnit.SECONDS.sleep(5);
		sleep.interrupt();
		busy.interrupt();
		System.out.println("sleep interrupted :"+sleep.isInterrupted());
		System.out.println("busy interrupted :"+busy.isInterrupted());

		TimeUnit.SECONDS.sleep(2);
		// 结果
//		sleep interrupted :false
//		busy interrupted :true
//		java.lang.InterruptedException: sleep interrupted
//		at java.lang.Thread.sleep(Native Method)
//		at java.lang.Thread.sleep(Thread.java:340)
//		at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
//		at com.stt.thread.part01_base.Ch08_InterruptThreadDemo$SleepRunner.run(Ch08_InterruptThreadDemo.java:16)
//		at java.lang.Thread.run(Thread.java:745)
	}

}
