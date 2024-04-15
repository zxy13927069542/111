package com.ying.tjava.collection;

import java.util.EnumMap;
import java.util.Map;

/**
 * 在存储常量时，EnumMap拥有更好的性能，且不需要重写Equals和hashCode
 */
public class TestEnumMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testEnumMap();
	}

	static void testEnumMap() {
		Map<WeekDay, String> map = new EnumMap<>(WeekDay.class);
		map.put(WeekDay.FRI, "星期五");
		map.put(WeekDay.MON, "星期一");
		String val = map.get(WeekDay.MON);
		System.out.println(val);
		
	}
}

enum WeekDay {
	SUN, MON, TUE, WED, THU, FRI, SAT;
	
	//	// 每个实例均为全局唯一:
    //	public static final Color RED = new Color();
    //	public static final Color GREEN = new Color();
    //	public static final Color BLUE = new Color();
}
