package com.stt.demo;

import java.lang.instrument.Instrumentation;

/**
 * @author Terrell She
 * @date 2020/4/7 10:34
 * @Email terrell.she@zoom.us
 * @Description
 */
public class RuntimeAgent {

	public static void agentmain(String args, Instrumentation inst) {
		System.out.println("load agent after main run.args="+args);
		Class[] classes = inst.getAllLoadedClasses();
		for (Class cls : classes) {
			System.out.println(cls.getName());
		}
		System.out.println("agent run has been completed.");
	}
}
