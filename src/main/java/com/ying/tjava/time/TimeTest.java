package com.ying.tjava.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class TimeTest {

//	public static void main(String[] args) {
//		// testLocalDateTime();
//		//	testZonedDateTime();
//		//	calculateArrivalAtNY();
//		testInstant();
//	}



	/**
	 * LocalDateTime没有时区，要用时区得用ZonedDateTime
	 */
	@Test
	 public void testLocalDateTime() {
		// time - > string
		// dateTime -> date -> string
		// dateTime -> time -> string
		// dateTime -> string
		LocalDateTime dateTime = LocalDateTime.now();
		LocalTime ltime = dateTime.toLocalTime();
		LocalDate date = dateTime.toLocalDate();
		// 严格按照ISO 8601的格式
		System.out.println(dateTime); // 2024-04-12T20:14:26.501786700
		System.out.println(ltime); // 20:14:26.501786700
		System.out.println(date); // 2024-04-12

		// string -> time 需要按照 ISO 8601 格式
		System.out.println(LocalDateTime.parse("2024-04-12T20:30:26"));

		// format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		System.out.println(format.format(dateTime));
	}

	/**
	 * ZonedDateTime带时区
	 */
	static void testZonedDateTime() {
		// create
		ZonedDateTime ztime = ZonedDateTime.now(ZoneId.of("GMT+09:00"));
		System.out.println(ztime); // 2024-04-12T22:31:44.902335700+09:00[GMT+09:00]

		// LocalDateTime <-> ZonedDateTime 注意：同一时间数字，但不同时区，不同时刻
		ZonedDateTime z1 = LocalDateTime.now().atZone(ZoneId.of("GMT+09:00"));
		System.out.println(z1); 					// 2024-04-12T21:39:19.883224+09:00[GMT+09:00] 与ztime不同时刻
		System.out.println(z1.toLocalDateTime());	//	2024-04-12T21:42:15.555002400
		
		//	change time zone 同一时刻下时区切换
		ZonedDateTime z2 = z1.withZoneSameInstant(ZoneId.of("GMT+08:00"));
		//	2024-04-12T20:45:14.438701100+08:00[GMT+08:00]
		System.out.println(z2);
	}
	
	//	某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
	static void calculateArrivalAtNY() {
		LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
	}
	
	static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
		ZonedDateTime zbjTime = ZonedDateTime.of(bj, ZoneId.of("GMT+08:00"));
		ZonedDateTime arrivalAt = zbjTime.plusHours(h).plusMinutes(m);
        return arrivalAt.withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
    }
	
	/**
	 * 时刻
	 */
	static void testInstant() {
		//	create
		Instant now = Instant.now();
		//	epoch second
		now.getEpochSecond();
		//	epoch 毫秒
		now.toEpochMilli();
		
		//	Instant -> LocalDateTime 2024-04-12T22:11:43.794392800
		System.out.println(LocalDateTime.ofInstant(now, ZoneId.systemDefault()));
		//	Instant -> ZonedDateTime 2024-04-12T22:12:55.768330700+08:00[Asia/Shanghai]
		System.out.println(ZonedDateTime.ofInstant(now, ZoneId.systemDefault()));
		
		//	LocalDateTime -> Instant
		Instant ins = LocalDateTime.now().toInstant(ZoneOffset.UTC);
		//	ZonedDateTime -> Instant
		Instant ins1 = ZonedDateTime.now().toInstant();
	}
}
