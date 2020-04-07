package com.stt.demo;

/**
 * @author Terrell She
 * @date 2020/4/7 13:37
 * @Email terrell.she@zoom.us
 * @Description 用于java agent的测试工作
 */
public class App {
	public static void main(String[] args) {
		System.out.println("APP start");
		System.out.println(new TargetClass().getNumber());
	}
}
