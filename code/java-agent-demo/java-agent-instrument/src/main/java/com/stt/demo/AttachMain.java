package com.stt.demo;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Terrell She
 * @date 2020/4/7 15:28
 * @Email terrell.she@zoom.us
 * @Description 负责检测jvm的启动并挂载agent
 */
public class AttachMain {

	public static void main(String[] args) throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
		new AttachThread(
				"D:\\note\\java-study\\code\\java-agent-demo\\java-agent-instrument\\target\\java-agent-instrument-1.0-SNAPSHOT.jar",
				VirtualMachine.list()).start();
	}

}

class AttachThread extends Thread {

	private final List<VirtualMachineDescriptor> listBefore;

	private final String jar;

	AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
		listBefore = vms;  // 记录程序启动时的 VM 集合
		jar = attachJar;
	}

	public void run() {
		VirtualMachine vm = null;
		List<VirtualMachineDescriptor> listAfter = null;
		try {
			int count = 0;
			while (true) {
				listAfter = VirtualMachine.list();
				for (VirtualMachineDescriptor vmd : listAfter) {
					if (!listBefore.contains(vmd)) {
						// 如果 VM 有增加，我们就认为是被监控的 VM 启动了
						// 这时，我们开始监控这个 VM
						System.out.println(vmd);
						vm = VirtualMachine.attach(vmd);
						break;
					}
				}
				Thread.sleep(500);
				count++;
				System.out.println(count);
				if (null != vm || count >= 30) {
					break;
				}
			}
			// 加载agent，执行完成后，释放
			vm.loadAgent(jar);
			vm.detach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
