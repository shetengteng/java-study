package com.stt.thread;

import java.nio.ByteBuffer;

/**
使用堆外内存，溢出分析
 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class ch33_DirectMemoryOOMDemo {
	public static void main(String[] args) {
		System.out.println("maxDirectMemory:"+sun.misc.VM.maxDirectMemory() / 1024 / 1024 + "mb");

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
	}
}