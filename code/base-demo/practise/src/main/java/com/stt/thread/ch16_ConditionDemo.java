package com.stt.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现精确唤醒
 */
public class ch16_ConditionDemo {


	public static void main(String[] args) {
		SharedPrint sharedPrint = new SharedPrint();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				sharedPrint.print5();
			}
		}).start();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				sharedPrint.print10();
			}
		}).start();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				sharedPrint.print15();
			}
		}).start();

	}
}

class SharedPrint{
	private String name = "A"; //  A B C 三个线程
	private Lock lock = new ReentrantLock();
	private Condition cA = lock.newCondition();
	private Condition cB = lock.newCondition();
	private Condition cC = lock.newCondition();

	public void print5(){
		lock.lock();
		try{
			while(name != "A"){
				cA.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("A");
			}
			name = "B";
			cB.signalAll();
		}catch (Exception e){
		}finally {
			lock.unlock();
		}
	}

	public void print10(){
		lock.lock();
		try{
			while(name != "B"){
				cB.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("B");
			}
			name = "C";
			cC.signalAll();
		}catch (Exception e){
		}finally {
			lock.unlock();
		}
	}
	public void print15(){
		lock.lock();
		try{
			while(name != "C"){
				cC.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println("C");
			}
			System.out.println("---------------");
			name = "A";
			cA.signalAll();
		}catch (Exception e){
		}finally {
			lock.unlock();
		}
	}
}