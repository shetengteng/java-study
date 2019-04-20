package com.stt.java8.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 * Created by Administrator on 2019/4/16.
 */
public class LocalTimeDemo {

	public static void main(String[] args) {

		LocalTime now = LocalTime.now();
		// 15:12:50.273
		System.out.println(now);

		LocalTime localTime = LocalTime.of(23,10,9);

		int hour = localTime.getHour();
		System.out.println(hour);

		int minute = localTime.getMinute();
		System.out.println(minute);

		int second = localTime.getSecond();
		System.out.println(second);


		// 将指定字符串转换为 时间对象
		LocalTime parse = LocalTime.parse("23:10:11");
		System.out.println(parse);

		LocalTime parse2 = LocalTime.parse("23点10分24秒", DateTimeFormatter.ofPattern("HH点mm分ss秒"));
		System.out.println(parse2);

		// 操作LocalTime 对象
		LocalTime localTime1 = localTime.withHour(1).plusSeconds(2).minusMinutes(9);
		System.out.println(localTime1);

		// 将指定字符串转换为 时间对象
		LocalTime localTime2 = LocalTime.parse("23:10:11",DateTimeFormatter.ofPattern("HH:mm:ss"));
		System.out.println(localTime2);

		String localTimeStr = localTime2.format(DateTimeFormatter.ofPattern("HH时mm分ss秒"));
		System.out.println(localTimeStr);
	}
}
