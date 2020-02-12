package com.stt.thread;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 - java8使用元空间替代了永久代
 - 使用本地内存，不使用虚拟机内存
 - 元空间存储
 - 虚拟机加载类的信息
 - 常量池
 - 静态变量
 - 即时编译后的代码
 - 示例：模拟溢出，不断生成类在元空间中
 - -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 */
public class ch35_MetaspaceOOMDemo {

	static class OOM{}

	public static void main(String[] args) {

		int i =0;
		while(true){
			i++;
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOM.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(o,args);
				}
			});
			enhancer.create();
		}
	}
}