package com.stt.thread.part01_base;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/5/26.
 */
public class Ch10_WaitNotifyDemo {

	static boolean flag = true;
	static Object lock = new Object();

	static class Wait implements Runnable{
		@Override
		public void run() {
			synchronized (lock){
				while(flag){
					System.out.println(Thread.currentThread().getName()+"flag is true, wait "+new Date());
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+"flag is false "+new Date());
			}
		}
	}

	static class Notify implements Runnable{
		@Override
		public void run() {
			synchronized (lock){
				System.out.println(Thread.currentThread().getName()+" notify has lock "+new Date());
				// 唤醒其他线程
				lock.notifyAll();
				flag = false;
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 在释放锁之前其他线程不会运行
			}
			// 再次加锁，与其他线程竞争
			synchronized (lock){
				System.out.println(Thread.currentThread().getName()+" notify has lock "+new Date());
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread wait = new Thread(new Wait(),"wait");
		Thread notify = new Thread(new Notify(),"notify");
		wait.start();
		TimeUnit.SECONDS.sleep(1);
		notify.start();
	}

//	结果
//	waitflag is true, wait Sun May 26 11:51:45 CST 2019
//	notify notify has lock Sun May 26 11:51:46 CST 2019
//	waitflag is false Sun May 26 11:51:51 CST 2019
//	notify notify has lock Sun May 26 11:51:51 CST 2019

}
