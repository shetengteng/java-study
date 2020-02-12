package com.stt.thread;

import java.util.concurrent.*;

public class ch21_ThreadPool_AbortPolicyDemo {
	public static void main(String[] args) {

		ExecutorService pool = new ThreadPoolExecutor(
				2,
				5,
				1L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
		);

		try{
			// 10个线程，超过了线程池8个任务的处理能力，抛出异常
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
