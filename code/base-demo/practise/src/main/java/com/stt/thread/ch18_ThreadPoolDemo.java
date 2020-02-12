package com.stt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ch18_ThreadPoolDemo {
	public static void main(String[] args) {

		ExecutorService pool = Executors.newFixedThreadPool(5);

		// 模拟10个用户办理业务，每个用户就是一个来自外部的请求线程
		try{
//			pool.submit(()->{ // submit用于处理代返回值的，可以传入callable和runnable
//				System.out.println();
//			});
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
