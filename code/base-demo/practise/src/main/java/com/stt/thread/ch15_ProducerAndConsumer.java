package com.stt.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者
 * 使用juc实现，注意边界条件，使用while，防止虚假唤醒
 */
public class ch15_ProducerAndConsumer {
	public static void main(String[] args) {
		Num num = new Num();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				num.increment();
			}
		},"t1").start();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				num.decrement();
			}
		},"t2").start();
	}
}

class Num {
	private int num = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() {
		lock.lock();
		try {
			while (num != 0) {
				condition.await();
			}
			num += 1;
			System.out.println(Thread.currentThread().getName()+" \t"+num);
			condition.signalAll();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}

	public void decrement(){
		lock.lock();
		try {
			while (num == 0) {
				condition.await();
			}
			num -= 1;
			System.out.println(Thread.currentThread().getName()+" \t"+num);
			condition.signalAll();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			lock.unlock();
		}
	}
}