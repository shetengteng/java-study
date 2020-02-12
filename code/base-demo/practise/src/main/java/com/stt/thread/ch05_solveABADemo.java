package com.stt.thread;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决
 */
public class ch05_solveABADemo {

	static AtomicReference<Integer> ref = new AtomicReference<>(100);

	// 使用带stamp的原子引用
	static AtomicStampedReference<Integer> sRef = new AtomicStampedReference<>(100,1);

	public static void main(String[] args) {

		// ABA问题的产生
		new Thread(()->{
			ref.compareAndSet(100,101);
			ref.compareAndSet(101,100);
		},"t1").start();

		new Thread(()->{
			// sleep 1s 让t1完成ABA操作
			try{Thread.sleep(1000);}catch (Exception e){}
			// 此时修改成功，但是100的值不是第一次的100
			System.out.println(ref.compareAndSet(100,102)+" current:"+ref.get());
		},"t2").start();


		try{Thread.sleep(3000);}catch (Exception e){}
		System.out.println("-------------------------");

		// ABA问题的解决，添加stamp
		new Thread(()->{
			// 线程3获取stamp
			System.out.println(Thread.currentThread().getName()+" 初始版本:"+sRef.getStamp());
			try{Thread.sleep(1000);}catch (Exception e){}
			// 线程3开始ABA操作
			sRef.compareAndSet(100,101,sRef.getStamp(),sRef.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+" 第2次版本:"+sRef.getStamp());
			sRef.compareAndSet(101,100,sRef.getStamp(),sRef.getStamp()+1);
			System.out.println(Thread.currentThread().getName()+" 第3次版本:"+sRef.getStamp());
		},"t3").start();

		new Thread(()->{
			// 线程4获取stamp
			int stamp = sRef.getStamp();
			System.out.println(Thread.currentThread().getName()+" 初始版本:"+stamp);
			try{Thread.sleep(3000);}catch (Exception e){}
			// 等线程3执行完ABA后进行修改操作
			System.out.println(Thread.currentThread().getName()+" 修改是否成功："
					+ sRef.compareAndSet(100,102,stamp,stamp+1)
					+" current:"+sRef.getReference()+" stamp:"+sRef.getStamp());
		},"t4").start();
	}

}
