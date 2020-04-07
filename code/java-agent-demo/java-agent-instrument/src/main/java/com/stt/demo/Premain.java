package com.stt.demo;

import sun.tools.java.ClassDefinition;

import java.lang.instrument.Instrumentation;

/**
 * @author Terrell She
 * @date 2020/4/7 13:43
 * @Email terrell.she@zoom.us
 * @Description
 */
public class Premain {
	/**
	 * 在主程序main方法之前运行
	 * @param agentArgs 传递的参数
	 * @param inst agent技术主要使用的api，可以用来改变和重新定义类的行为
	 */
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("premain");
		// main方法执行之前，每装载一个类，就会调用该transformer一次，判断是否要进行类的转换
		inst.addTransformer(new Transformer());
	}
}


