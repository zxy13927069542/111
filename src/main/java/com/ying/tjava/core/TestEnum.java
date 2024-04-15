package com.ying.tjava.core;

public class TestEnum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		enumUsage();
	}
	
	static void enumUsage() {
		var day = WeekDay.SUN;
		//	enum类型的每个常量在JVM中只有一个唯一实例，所以可以直接用==比较：
		if (day == WeekDay.SAT || day == WeekDay.WED) {
			System.out.println(day.name());
		}
		System.out.println(day.name());
	}
	

}

enum WeekDay {
	SUN, MON, TUE, WED, THU, FRI, SAT;
	
	//	// 每个实例均为全局唯一:
    //	public static final Color RED = new Color();
    //	public static final Color GREEN = new Color();
    //	public static final Color BLUE = new Color();
}