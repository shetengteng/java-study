package com.stt.thread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现自旋锁
 */
public class ch07_SpinLockDemo {

	public static void main(String[] args) {
		MyLock2 lock = new MyLock2();

		new Thread(()->{
			lock.lock();
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			lock.unlock();
		},"t1").start();


		new Thread(()->{
			lock.lock();
			try { Thread.sleep(3000); } catch (InterruptedException e) { }
			lock.unlock();
		},"t2").start();
	}
}

class MyLock{

	// 自旋锁标识
	AtomicBoolean flag=new AtomicBoolean(false);

	public void lock(){
		while(flag.compareAndSet(false,true)){
			// 自旋
		}
	}

	public void unlock(){
		if(!flag.compareAndSet(true,false)){
			System.out.println("unlock fail");
		}
	}
}

class MyLock2{
	AtomicReference<Thread> t = new AtomicReference<>();
	public void lock(){
		System.out.println(Thread.currentThread().getName() + " try obtain lock");
		while(!t.compareAndSet(null,Thread.currentThread())){

		}
	}

	public void unlock(){
		System.out.println(Thread.currentThread().getName() + " release lock");
		t.compareAndSet(Thread.currentThread(),null);
	}

}