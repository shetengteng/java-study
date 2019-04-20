package com.stt;

import com.stt.compiler.DynamicCompiler;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/1/31.
 */
public class BaseTest {

	@Test
	public void testSysDir(){
		System.out.println(System.getProperty("user.dir"));
		//E:\java\code\base-demo\common 本地运行
		//E:\java\code\base-demo\common\target 编译后运行
		// 表示该路径是项目运行的路径
	}

	@Test
	public void testCompile() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		String javaFileName = "Helloworld.java";
		String javaCode = "package com.stt;\n" +
				"\n" +
				"import lombok.extern.slf4j.Slf4j;\n" +
				"\n" +
				"/**\n" +
				" * Created by Administrator on 2019/2/2.\n" +
				" */\n" +
				"@Slf4j\n" +
				"public class Helloworld {\n" +
				"\tpublic void sayHello(){\n" +
				"\t\tSystem.out.println(\"hello world\");\n" +
				"\t}\n" +
				"}\n";
		Class<?> compile = DynamicCompiler.compile(javaFileName, javaCode);
		Object o = compile.newInstance();
		Method sayHello = compile.getMethod("sayHello");
		sayHello.invoke(o);
	}

}
