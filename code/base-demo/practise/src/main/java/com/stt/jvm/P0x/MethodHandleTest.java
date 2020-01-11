package com.stt.jvm.P0x;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 使用methodHandle代替反射调用其他类方法
 */
public class MethodHandleTest {
	public static void main(String[] args) {
		(new MethodHandleTest().new Son()).thinking();
	}

	class GrandFather{
		void thinking(){
			System.out.println("grand father"); // jdk1.7执行结果
		}
	}

	class Father extends GrandFather{
		@Override
		void thinking(){
			System.out.println("father"); //  jdk 1.8执行结果 lookup 调用者敏感
		}
	}

	class Son extends Father{
		@Override
		void thinking(){
			try {
				MethodType mt = MethodType.methodType(void.class);
				MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
				mh.invoke(this);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}

}
