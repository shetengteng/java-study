package com.stt.thread;

/**
 * 测试volatile不保证原子性
 */
public class ch02_volatileDemo {

	public static void main(String[] args) {
		MyData2 d = new MyData2();

		// 开20个线程，每个线程执行1000次，如果是原子操作，那么d的值是2万
		for (int i = 0; i < 20; i++) {
			new Thread(()->{
				for (int j = 0; j < 1000; j++) {
					d.addOne();
				}
			},"thread"+i).start();
		}
		// 如果活跃线程超过2个，那么main线程放弃当前cpu执行资源
		// 当创建的20个线程执行完成后剩下main线程和gc线程共2个线程
		while(Thread.activeCount() > 2){
			// 当前线程放弃本次执行
			Thread.yield();
		}
		System.out.println(d.num);
	}
}

class MyData2{
	volatile int num = 0;

	public void addOne(){
		// num ++ 在cpu中是3步操作，拷贝num到工作空间; tmp = num + 1; num = tmp;
		num ++;
	}
}