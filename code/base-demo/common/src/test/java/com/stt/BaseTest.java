package com.stt;

import org.junit.Test;

/**
 * Created by Administrator on 2019/1/31.
 */
public class BaseTest {

	@Test
	public void testSysDir(){
		System.out.println(System.getProperty("user.dir"));
		//E:\java\code\base-demo\common 本地运行
		//E:\java\code\base-demo\common\target 编译后运行
		// 表示该路径是项目运行的路径
	}

}
