package com.stt.jvm.P01_OOM;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:MaxPermSize=10M -XX:PermSize=10M
 * jdk1.8 更改为 -XX:MaxMetaspaceSize=10M -XX:MetaspaceSize=10M
 */
public class C05_JavaMethodAreaOOM {

	public static void main(String[] args) {
		while (true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
					return methodProxy.invokeSuper(objects,args);
				}
			});
			enhancer.create();
		}
	}

	static class OOMObject{}
}
