package com.stt.java8.time;

import java.time.*;

/**
 * Created by Administrator on 2019/4/17.
 */
public class ZoneDateTimeDemo {

	public static void main(String[] args) {

		//时区操作
		System.out.println(ZoneId.systemDefault().getId());

		ZoneId shanghaiZone = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
		System.out.println(shanghaiZone);

		LocalDateTime localDateTime = LocalDateTime.of(2019,4,17,1,7,9);
		ZonedDateTime shanghaiZoneDateTime = localDateTime.atZone(shanghaiZone);

		//2019-04-17T01:07:09+08:00[Asia/Shanghai]
		System.out.println(shanghaiZoneDateTime);

		Instant instant = Instant.now();
		// 是0时区的时间
		System.out.println(instant);
		// 本地时间戳
		System.out.println(instant.getEpochSecond());


		ZonedDateTime shanghaiInstant = instant.atZone(shanghaiZone);
		System.out.println(shanghaiInstant);


		LocalDateTime formInstant1= LocalDateTime.ofInstant(instant,shanghaiZone);
		System.out.println(formInstant1);


		// 当前时间 -8-5
		LocalDateTime formInstant2= LocalDateTime.ofInstant(instant,ZoneId.of(ZoneId.SHORT_IDS.get("EST")));
		System.out.println(formInstant2);

	}
}
