package com.stt.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2019/4/16.
 */
public class LocalDateTimeDemo {

	public static void main(String[] args) {
		// LocalDate和LocalTime的合体，但是不带有时区信息
		// 2019-04-14T23:10:09

		LocalDate date = LocalDate.of(2019,4,14);
		LocalTime localTime = LocalTime.of(23,10,9);

		LocalDateTime dateTime = LocalDateTime.of(date,localTime);
		System.out.println(dateTime);

		LocalDateTime dateTime2 = date.atTime(localTime);
//		LocalDateTime dateTime2 = date.atTime(23,10,9);
		System.out.println(dateTime2);

		LocalDateTime dateTime3 = localTime.atDate(date);

		LocalDateTime dateTime4 = LocalDateTime.of(2019,4,14,23,10,11);
		LocalDateTime dateTime5 = LocalDateTime.of(2019, Month.APRIL,14,23,10,11);

		LocalDate date2 = dateTime.toLocalDate();
		LocalTime time2 = dateTime.toLocalTime();

		LocalDateTime localDateTime = LocalDateTime.parse("2019-04-17 10:10:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(localDateTime);

		String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		System.out.println(localDateTimeStr);

	}
}
