package com.stt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ch20_ThreadPoolDemo {
	public static void main(String[] args) {

		ExecutorService pool = Executors.newCachedThreadPool();

		// 模拟10个用户办理业务，每个用户就是一个来自外部的请求线程
		try{
			for (int i = 0; i < 10; i++) {
				pool.execute(()->{ // 无返回值，只能传入runnable
					System.out.println(Thread.currentThread().getName()+" run");
				});
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			pool.shutdown();
		}

	}
}
