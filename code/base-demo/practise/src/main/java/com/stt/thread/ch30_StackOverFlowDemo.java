package com.stt.thread;

/**
 *
 */
public class ch30_StackOverFlowDemo {
	public static void main(String[] args) {
		m(); // 递归调用可能导致栈溢出异常
	}

	static void m(){
		m();
	}
}
