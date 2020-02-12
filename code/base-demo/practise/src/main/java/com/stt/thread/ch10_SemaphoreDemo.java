package com.stt.thread;

import java.util.concurrent.Semaphore;

public class ch10_SemaphoreDemo {
	public static void main(String[] args) {

		Semaphore semaphore = new Semaphore(6);
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				try {
					semaphore.acquire();
					String name = Thread.currentThread().getName();
					System.out.println(name+" 获取到信号量");
					Thread.sleep(1000);
					System.out.println(name + " 运行1s后离开");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					semaphore.release();
				}
			},"t"+i).start();
		}
	}
}
