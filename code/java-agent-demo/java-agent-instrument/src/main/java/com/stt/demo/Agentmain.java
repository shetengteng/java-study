package com.stt.demo;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author Terrell She
 * @date 2020/4/7 10:34
 * @Email terrell.she@zoom.us
 * @Description
 */
public class Agentmain {

	public static void agentmain(String args, Instrumentation inst) throws UnmodifiableClassException, ClassNotFoundException, InterruptedException {
		System.out.println("agent main start");
//		inst.addTransformer(new Transformer());
//		inst.retransformClasses(TargetClass.class); // 没有生效

		ClassDefinition def = new ClassDefinition(TargetClass.class, Transformer.getBytesFromFile(Transformer.path));
		inst.redefineClasses(new ClassDefinition[] { def });
		System.out.println("Agent Main Done");
	}
}
