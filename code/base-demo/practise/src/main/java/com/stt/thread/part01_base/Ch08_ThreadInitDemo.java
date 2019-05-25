package com.stt.thread.part01_base;

import org.springframework.util.Assert;

import java.security.AccessControlContext;

/**
 * 构造一个线程所需要的参数
 * Created by Administrator on 2019/5/25.
 */
public class Ch08_ThreadInitDemo {


	public static void main(String[] args) {

		Thread
	}


	private void init(ThreadGroup group,
	                  Runnable target, String name, long stackSize,
	                  AccessControlContext controlContext){
		Assert.notNull(name,"name cannot be null");
		// 当前的线程就是该线程的父线程
		Thread parent = Thread.currentThread();


	}


}
