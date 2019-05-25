package com.stt.thread.part01_base;

import sun.misc.Signal;

import javax.management.monitor.Monitor;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *
 * 使用JMX查看main函数执行时，有哪些线程
 * Created by Administrator on 2019/5/24.
 */
public class Ch06_JMXMainDemo {
	public static void main(String[] args) {
		// 获取java线程管理MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		// 不需要获取同步的monitor和synchronizer信息
		// 只获取线程和线程堆的信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
		// 打印线程信息
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println(String.format("[%s] %s",threadInfo.getThreadId(),threadInfo.getThreadName()));
		}
	}
	// 结果
	//[6] Monitor Ctrl-Break
	//[5] Attach Listener
	//[4] Signal Dispatcher
	//[3] Finalizer
	//[2] Reference Handler
	//[1] main
}
