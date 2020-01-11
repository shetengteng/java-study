package com.stt.jvm.P01_OOM;

/**
 * vm args: -Xss128k
 */

public class C02_JavaVMStackSOF {

	int stackLength = 1;

	void stackLeak(){
		stackLength += 1;
		stackLeak();
	}

	public static void main(String[] args) {
		C02_JavaVMStackSOF oom = new C02_JavaVMStackSOF();
		try{
			oom.stackLeak();
		}catch (Throwable e){
			System.out.println("stackLength:"+oom.stackLength);
			throw e;
		}
	}
}
