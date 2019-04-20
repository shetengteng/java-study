package com.stt.java8.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Created by Administrator on 2019/4/16.
 */
public class DurationDemo {

	public static void main(String[] args) {

		// Duration 用于计算2个时间的间隔，传入的参数必须是相同类型的
		// 由于是计算间隔是s，不支持LocalDate对象

		LocalDateTime dateTime1 = LocalDateTime.now();
		LocalDateTime dateTime2 = dateTime1.plusHours(2).plusMinutes(2);

		Duration between = Duration.between(dateTime1, dateTime2);
		System.out.println(between.getSeconds());

		Duration between2 = Duration.between(dateTime2, dateTime1);
		System.out.println(between2.getSeconds());

		Duration abs = Duration.between(dateTime2, dateTime1).abs();
		System.out.println(abs.getSeconds());

		LocalTime time1 = LocalTime.of(11,11,11);
		LocalTime time2 = time1.plusHours(1).plusSeconds(3);

		Duration between3 = Duration.between(time1,time2);
		System.out.println(between3.getSeconds());
		// 如果是日期类型的使用Duration则会抛出类型支持异常

		long seconds = between.getSeconds();
		int nano = between.getNano();
	}

}
