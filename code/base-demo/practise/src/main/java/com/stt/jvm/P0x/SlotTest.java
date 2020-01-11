package com.stt.jvm.P0x;


/**
 * 测试 栈帧中局部变量表slot分配后垃圾回收情况
 * -verbose:gc
 */
public class SlotTest {
	public static void main(String[] args) {
		{
			byte[] placeholder = new byte[64*1024*1024];
		}
		int a = 0;
		System.gc();
	}
}
