package com.stt.java8.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

/**
 * Created by Administrator on 2019/4/16.
 */
public class InstantDemo {

	public static void main(String[] args) {
		// 机器时间，从UTC时间1970-01-01 00:00:00 计时的秒数

		Instant now = Instant.now();
		// 获取系统的时间戳
		System.out.println(now.getEpochSecond());
		// 获取ms
		System.out.println(now.get(ChronoField.MILLI_OF_SECOND));
		// 获取ns
		System.out.println(now.get(ChronoField.NANO_OF_SECOND));


		// 当前时区的时间点的时间戳
		Instant parse = Instant.parse("2019-04-17T14:46:10Z");
		System.out.println(parse.getEpochSecond());

	}

}
