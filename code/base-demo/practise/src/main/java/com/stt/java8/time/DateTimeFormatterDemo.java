package com.stt.java8.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * Created by Administrator on 2019/4/17.
 */
public class DateTimeFormatterDemo {

	public static void main(String[] args) {

		// DateTimeFormatter是线程安全的

		// toString
		LocalDate localDate = LocalDate.of(2019,4,17);
//		20190417
		System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
//		2019-04-17
		System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));

		LocalTime localTime = LocalTime.of(12,12,12);
//		error
//		System.out.println(localTime.format(DateTimeFormatter.BASIC_ISO_DATE));
//		12:12:12
		System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));



		LocalDateTime localDateTime = localTime.atDate(localDate);
//		2019-04-17T12:12:12
		System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yy MMMM d", Locale.CHINA)));

		// toDate
		LocalDate parse1 = LocalDate.parse("20190418", DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(parse1);

		LocalDateTime parse2 = LocalDateTime.parse("2019-04-18 23:22:34",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(parse2);


	}
}
