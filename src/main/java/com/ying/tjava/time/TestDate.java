package com.ying.tjava.time;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * java.util 时间类
 *  Date无法进行日期的加减，也无法修改时区
 *  Calendar可以用于获取并设置年、月、日、时、分、秒，它和Date比，主要多了一个可以做简单的日期和时间运算的功能
 */
public class TestDate {

	public static void main(String[] args) {
		//	testLocale();
		//	testEpoch();
		//	testFormat();
		testCalendar();
	}
	
	static void testLocale() {
		System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(33668));
	}

	// 	epoch -> date -> time string
	//	date -> epoch
	static void testEpoch() {
		//	epoch -> date -> time string
		//	自1970来的毫秒数
		long epoch = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MMM/dd");
		//	2024/04/12
		System.out.println(format.format(new Date(epoch)));
		
		// date -> epoch
		Date date = new Date();
		System.out.println(date.getTime());
	}
	
	static void testFormat() {
		//	date -> string use java.text.SimpleDateFormat
		//	2024-04-12 19:29:15
		//	E: 	Day name in week 周五
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd EE HH:mm:ss");
		System.out.println(format.format(new Date()));
	}
	
	static void testCalendar() {
		//	calendar -> date -> string
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat();
		System.out.println(format.format(cal.getTime()));
		
		//	change time zone and print
		format.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
		//	cal.setTimeZone(TimeZone.getDefault());						//	默认时区
		//	cal.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));			//	东京
		//	cal.setTimeZone(TimeZone.getTimeZone("America/New_York"));	//	纽约
		//	cal.getTime() Date无时区信息
		System.out.println(format.format(cal.getTime()));	
		
	}
}
