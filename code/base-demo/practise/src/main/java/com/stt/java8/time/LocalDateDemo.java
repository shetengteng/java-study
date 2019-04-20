package com.stt.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**`
 * Created by Administrator on 2019/4/16.
 */
public class LocalDateDemo {


	public static void main(String[] args) {
		// LocalDate 只包含了日期信息，不包含时间信息

		LocalDate date = LocalDate.of(2019,4,14);
		System.out.println(date);

		int year = date.getYear();
		System.out.println(year);

		Month month = date.getMonth();
		System.out.println(month);
		System.out.println(month.getValue());

		int dayOfMonth = date.getDayOfMonth();
		System.out.println(dayOfMonth);

		DayOfWeek dayOfWeek = date.getDayOfWeek();
		System.out.println(dayOfWeek);
		System.out.println(dayOfWeek.getValue());

		int lengthOfMonth = date.lengthOfMonth();
		System.out.println(lengthOfMonth);

		boolean leapYear = date.isLeapYear();
		System.out.println(leapYear);

		// 获取当前日期
		LocalDate now = LocalDate.now();
		System.out.println(now);

		year = date.get(ChronoField.YEAR);
		System.out.println(year);

		int monthOfYear = date.get(ChronoField.MONTH_OF_YEAR);
		System.out.println(monthOfYear);

		dayOfMonth = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println(dayOfMonth);

		// 抛出异常，不支持该字段
//		int instantSeconds = date.get(ChronoField.INSTANT_SECONDS);
//		System.out.println(instantSeconds);

		// 操作LocalDate
		LocalDate date1 = date.withYear(2018).minusDays(1).plusMonths(2);
		System.out.println(date1);

		LocalDate localDate = LocalDate.parse("2019-04-17");
		System.out.println(localDate);

		String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		System.out.println(dateStr);

	}

}
