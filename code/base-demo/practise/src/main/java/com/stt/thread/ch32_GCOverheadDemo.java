package com.stt.thread;

import java.util.ArrayList;
import java.util.List;

/**
 - GC回收时间过长会抛出OOM:GC overhead limit exceeded
 - 超过98%的时间做GC回收，并且回收了不到2%的堆内存
 - 连续回收多次，都只回收了2%的内存的极端情况会抛出该异常
 - CPU使用率一直100%，而GC没有任何效果
 -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class ch32_GCOverheadDemo {
	public static void main(String[] args) {
		int i = 0;
		List<String> list = new ArrayList<>();
		while(true){
			// 获取常量池中的引用，不断创建对象
			list.add(String.valueOf(++i).intern());
		}
	}
}