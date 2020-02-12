package com.stt.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用阻塞队列实现生产者消费者
 */
public class ch16_ProducerAndConsumer {

	public static void main(String[] args) {
		MySource mySource = new MySource(new ArrayBlockingQueue(10));
		new Thread(()->{
			try {
				mySource.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"producer").start();

		new Thread(()->{
			try {
				mySource.consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"consumer").start();
	}
}

class MySource{
	private volatile boolean flag = true;
	private BlockingQueue<String> queue;
	private AtomicInteger atomicInteger = new AtomicInteger(); // 生产的值
	MySource(BlockingQueue queue){
		this.queue = queue;
	}

	public void produce() throws InterruptedException {
		String data;
		while(flag){
			data = atomicInteger.incrementAndGet()+"";
			if(queue.offer(data,2L, TimeUnit.SECONDS)){
				System.out.println(Thread.currentThread().getName()+" producer "+data);
			}
			Thread.sleep(1000);
		}
		System.out.println("producer end");
	}

	public void consumer() throws InterruptedException {
		while(flag){
			String result = queue.poll(2L, TimeUnit.SECONDS);
			if(result == null){
				flag = false;
				System.out.println("consumer timeout exit");
			}else{
				System.out.println("consumer "+result);
			}
		}
		System.out.println("consumer end");
	}

	public void close(){
		flag = false;
	}
}
