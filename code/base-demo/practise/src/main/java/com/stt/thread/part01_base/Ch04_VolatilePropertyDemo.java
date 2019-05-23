package com.stt.thread.part01_base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用于测试volatile特性
 * 如下的写法依然不是线程安全的
 * volatile只有可见性，单个元素的读写具有原子性，那么 volatile++符合操作没有原子性
 * Created by Administrator on 2019/5/22.
 */
public class Ch04_VolatilePropertyDemo {


	static class VolatileBoolean{
		int a = 0;
		boolean flag = false;
		public void write(){
			if(!flag){
				a ++;
				flag = true;
			}
		}
		public void read(){
			if(flag){
				System.out.println(a);
				flag = false;
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		VolatileBoolean v = new VolatileBoolean();

		Thread t1 = new Thread(()->{

			for(int i=0;i<10000;i++){
				v.write();
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t3 = new Thread(()->{

			for(int i=0;i<10000;i++){
				v.write();
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t4 = new Thread(()->{

			for(int i=0;i<10000;i++){
				v.write();
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(()->{
			for(;;){
				v.read();
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t2.start();
		t3.start();
		t4.start();
		t1.start();

		t2.join();
	}
}
