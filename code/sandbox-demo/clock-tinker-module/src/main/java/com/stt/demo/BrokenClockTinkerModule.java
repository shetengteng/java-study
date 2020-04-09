package com.stt.demo;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;

@MetaInfServices(Module.class)
@Information(id = "broken-clock-tinker")
public class BrokenClockTinkerModule implements Module {

	// 沙箱的核心类
	@Resource
	private ModuleEventWatcher moduleEventWatcher;

	@Command("repairCheckState")
	public void repairCheckState() {
		/**
		 * 拦截{@code com.taobao.demo.Clock#checkState()}方法，当这个方法抛出异常时将会被
		 * AdviceListener#afterThrowing()所拦截
		 */
		new EventWatchBuilder(moduleEventWatcher)
				.onClass("com.stt.demo.Clock")
				.onBehavior("checkState")
				.onWatch(new AdviceListener() {
					// 方法抛出异常后处理
					@Override
					protected void afterThrowing(Advice advice) throws Throwable {
						// 通过ProcessController改变原有方法的执行，中断当前方法执行，返回指定值
						// void 就返回 null
						ProcessController.returnImmediately(null);
					}
				});
	}
}