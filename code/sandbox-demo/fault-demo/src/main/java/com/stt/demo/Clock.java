package com.stt.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Clock {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	final void checkState(){
		throw new IllegalStateException("STATE ERROR");
	}

	final Date now(){
		return new Date();
	}

	final String report(){
		checkState();
		return sdf.format(now());
	}

	final void loopReport() throws InterruptedException {
		while(true){
			try{
				System.out.println(report());
			}catch (Throwable t){
				t.printStackTrace();
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 该loopReport循环打印错误信息
		new Clock().loopReport();
	}
}
