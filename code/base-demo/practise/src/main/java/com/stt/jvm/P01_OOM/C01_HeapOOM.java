package com.stt.jvm.P01_OOM;


import java.util.ArrayList;
import java.util.List;

/**
 * 实践：OutOfMemoryError
 *
 * 限制java堆大小为20MB，不可扩展，将堆最小值Xms和最大值Xmx设置一样，避免堆自动扩展
 * 设置HeapDumpOnOutOfMemoryError 在虚拟机出现异常时，Dump出当前的内存堆快照
 *
 * VM args : -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 *
 */

public class C01_HeapOOM {

	static class OOMObject{ }

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true){
			list.add(new OOMObject());
		}
	}
}
