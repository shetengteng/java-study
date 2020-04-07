package com.stt.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author Terrell She
 * @date 2020/4/7 13:44
 * @Email terrell.she@zoom.us
 * @Description
 */
public class Transformer implements ClassFileTransformer {

	public static final String path = "/TargetClass.class.2";

	/**
	 * 参数：
		 * loader - 定义要转换的类加载器；如果是引导加载器，则为 null
		 * className - 完全限定类内部形式的类名称和 The Java Virtual Machine Specification 中定义的接口名称。例如，"java/util/List"。
		 * classBeingRedefined - 如果是被重定义或重转换触发，则为重定义或重转换的类；如果是类加载，则为 null
		 * protectionDomain - 要定义或重定义的类的保护域
		 * classfileBuffer - 类文件格式的输入字节缓冲区（不得修改）
	 * 返回：
	    * 一个格式良好的类文件缓冲区（转换的结果），如果未执行转换,则返回 null
    * 抛出：
	    * IllegalClassFormatException - 如果输入不表示一个格式良好的类文件
	 */
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		// 针对该类进行转换
		if(className.equals("com/stt/demo/TargetClass")){
			byte[] b= getBytesFromFile(path);
			System.out.println(b);
			return b;
		}
		return null;
	}

	public static byte[] getBytesFromFile(String path) {

		try{
			File file = new File(path);
			FileInputStream inputStream = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];

			int offset = 0;
			int numRead = 0;
			// 读取文件，没有读完会继续读取，read 返回-1 表示读取完毕
			while(offset < bytes.length && (numRead = inputStream.read(bytes,offset,bytes.length-offset)) >= 0){
				offset += numRead;
			}
			if(offset < bytes.length){
				// 读取结束但是没有读取完整
				throw new IOException("could not completely read file"+file.getName());
			}
			inputStream.close();
			return bytes;
		}catch (Exception e){
			System.out.println("ERROR"+e.getClass().getName());
			return null;
		}
	}
}
