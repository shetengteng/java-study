package com.stt.demo;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author Terrell She
 * @date 2020/4/7 13:43
 * @Email terrell.she@zoom.us
 * @Description
 */
public class Premain2 {

	// 写法2
	public static void premain(String agentArgs, Instrumentation inst)  throws ClassNotFoundException, UnmodifiableClassException {
		ClassDefinition def = new ClassDefinition(TargetClass.class, Transformer.getBytesFromFile(Transformer.path));
		inst.redefineClasses(new ClassDefinition[] { def });
		System.out.println("success");
	}
}


