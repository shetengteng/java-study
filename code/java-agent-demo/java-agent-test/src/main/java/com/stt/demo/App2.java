package com.stt.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author Terrell She
 * @date 2020/4/7 13:37
 * @Email terrell.she@zoom.us
 * @Description 用于java agent的测试工作，启动后attach agent测试
 */
public class App2 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("APP start");
		System.out.println(new TargetClass().getNumber());

		while (true) {
			System.out.println(new TargetClass().getNumber());
			TimeUnit.SECONDS.sleep(3);
		}
	}
}
