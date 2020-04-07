package com.stt.demo;

import java.lang.instrument.Instrumentation;

/**
 * @author Terrell She
 * @date 2020/4/7 9:53
 * @Email terrell.she@zoom.us
 * @Description
 */
public class TestAgent {
	/**
	 * 在主程序main方法之前运行
	 * @param agentArgs 传递的参数
	 * @param inst agent技术主要使用的api，可以用来改变和重新定义类的行为
	 */
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("premain start");
		System.out.println(agentArgs);
	}
}
