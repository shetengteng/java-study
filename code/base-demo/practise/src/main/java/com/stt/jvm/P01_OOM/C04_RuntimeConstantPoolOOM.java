package com.stt.jvm.P01_OOM;


public class C04_RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		String str1 = new StringBuilder("ss").append("oom").toString();
		// true
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		// false
		System.out.println(str2.intern() == str2);
	}
}
