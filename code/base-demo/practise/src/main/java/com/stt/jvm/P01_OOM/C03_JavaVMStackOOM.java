package com.stt.jvm.P01_OOM;

/**
 * vm args: -Xss2M
 */
public class C03_JavaVMStackOOM {

	private void dontStop(){
		while (true){}
	}

	public static void main(String[] args) throws Throwable{
		C03_JavaVMStackOOM oom = new C03_JavaVMStackOOM();
		oom.stackLeakByThread();
	}

	public void stackLeakByThread(){
		while(true){
			new Thread(()->{
				dontStop();
			}).start();
		}
	}
}
