package com.stt.thread.part01_base;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于测试volatile特性
 * 如下的写法依然不是线程安全的
 * volatile只有可见性，单个元素的读写具有原子性，那么 volatile++符合操作没有原子性
 * Created by Administrator on 2019/5/22.
 */
public class Ch03_VolatilePropertyDemo {

	/**
	 *  使用volatile字段
	 */
	static class VolatileExample1{

		volatile long v = 0L;
		public void set(long l){
			v = l;
		}
		public void increment(){
			v++;
		}
		public long get(){
			return v;
		}
	}

	static class VolatileExample3{

		volatile int v = 0;
		public void set(int l){
			v = l;
		}
		public void increment(){
			v++;
		}
		public int get(){
			return v;
		}
	}

	static class VolatileExample0{

		int v = 0;
		public void set(int l){
			v = l;
		}
		public void increment(){
			v++;
		}
		public int get(){
			return v;
		}
	}

	static class VolatileExample2{
		long v = 0L;
		public synchronized void set(long l){
			v = l;
		}
		public void increment(){
			long tmp = get();
			tmp += 1;
			set(tmp);
		}
		public synchronized long get(){
			return v;
		}
	}


	static class VolatileExample4{

		volatile long v = 0L;
		public void set(long l){
			v = l;
		}
		// 对复合操作具有顺序一致性的写法
		public synchronized void increment(){
			v++;
		}
		public long get(){
			return v;
		}
	}

	public static void main(String[] args) {

		VolatileExample0 v1 = new VolatileExample0();
//		VolatileExample1 v1 = new VolatileExample1();
//		VolatileExample2 v2 = new VolatileExample2();
//		VolatileExample3 v2 = new VolatileExample3();
		VolatileExample4 v2 = new VolatileExample4();

		List<Thread> ts = new ArrayList<>();
		List<Thread> ts2 = new ArrayList<>();
		for(int i = 0;i<1000;i++){
			ts.add(new Thread(() -> {
				for(int j =0;j<100;j++){
					v1.increment();
				}
			}));
			ts2.add(new Thread(() -> {
				for(int j=0;j<100;j++){
					v2.increment();
				}
			}));
		}
		for(Thread t:ts){
			t.start();
		}
		for(Thread t:ts){
			try{
				t.join();
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		System.out.println(v1.get());

		for(Thread t:ts2){
			t.start();
		}
		for(Thread t:ts2){
			try{
				t.join();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		System.out.println(v2.get());
	}
}
