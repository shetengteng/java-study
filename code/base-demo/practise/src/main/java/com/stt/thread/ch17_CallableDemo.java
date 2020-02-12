package com.stt.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ch17_CallableDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());

		new Thread(futureTask,"callable").start();

		while(!futureTask.isDone()){
			// 使用isDone判断callable是否执行完成，执行其他代码
			// 如果直接使用get，如果没有执行完成则会阻塞
		}

		System.out.println(futureTask.get());

	}
}

class MyCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		return "my callable";
	}
}