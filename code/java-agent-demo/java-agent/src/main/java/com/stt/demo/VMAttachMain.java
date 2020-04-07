package com.stt.demo;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author Terrell She
 * @date 2020/4/7 10:40
 * @Email terrell.she@zoom.us
 * @Description
 */
public class VMAttachMain {

	/**
	 * 使用虚拟机的attach机制
	 * @param args
	 */
	public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
		VirtualMachine vm = VirtualMachine.attach("10532");
		vm.loadAgent("D:\\note\\java-study\\code\\java-agent\\target\\java-agent-1.0-SNAPSHOT.jar");
	}
}
