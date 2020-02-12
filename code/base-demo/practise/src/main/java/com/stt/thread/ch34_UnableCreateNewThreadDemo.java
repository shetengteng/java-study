package com.stt.thread;

/**
 - 创建线程过多，超过服务器限制
 - 在Linux中，对单个进程的创建线程数有个数限制（1024），如果超过该限制则会报该错误
 */
public class ch34_UnableCreateNewThreadDemo {
	public static void main(String[] args) {
		for (int i =0;;i++){
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+" run");
				try {
					Thread.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) { e.printStackTrace(); }
			},"t"+i).start();
		}
	}
}