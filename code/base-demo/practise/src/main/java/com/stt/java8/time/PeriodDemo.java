package com.stt.java8.time;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Administrator on 2019/4/17.
 */
public class PeriodDemo {

	public static void main(String[] args) {
		// 用于计算2个日期之间的天数间隔等
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = date1.plusDays(2).plusMonths(1);

		Period between = Period.between(date1, date2);
		System.out.println(between.getDays());
		System.out.println(between.getMonths());


		Period of = Period.of(1, 2, 3);
		System.out.println(of.getDays());
		System.out.println(of.getMonths());
		System.out.println(of.getYears());

		System.out.println(Period.ofWeeks(3).getDays());
		// 月份和年的days无法计算，因为有31天和30天，闰年等，所以就按照真实添加的天数计算
		System.out.println(Period.ofMonths(1).getDays());

	}
}
