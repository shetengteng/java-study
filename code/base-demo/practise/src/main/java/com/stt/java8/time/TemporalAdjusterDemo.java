package com.stt.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Administrator on 2019/4/17.
 */
public class TemporalAdjusterDemo {

	public static void main(String[] args) {

		LocalDate localDate = LocalDate.of(2019,4,20);

		// 当前日期的第一个周五
		LocalDate nextOrSame = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
		System.out.println(nextOrSame);
		// 当前月的最后一天
		LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(lastDayOfMonth);

		LocalDate nextOrSame2 = localDate.with(nextOrSameByInt(5));
		System.out.println(nextOrSame2);

	}

	public static TemporalAdjuster nextOrSameByInt(int dayOfWeek){

		return new TemporalAdjuster(){
			@Override
			public Temporal adjustInto(Temporal temporal) {
				int calDow = temporal.get(DAY_OF_WEEK);
				if (calDow == dayOfWeek) {
					return temporal;
				}
				int daysDiff = calDow - dayOfWeek;
				return temporal.plus(daysDiff >= 0 ? 7 - daysDiff : -daysDiff, DAYS);
			}
		};
	}
}
