package com.ying.tjava.annotation;

//@Report(type=1, level=Level.WARNNING, value="一些需要警惕的东西出现了")
@Report(type=0, level=Level.INFO, value="需要给你一些提示")
public class TestAnnotation {

	public static void main(String[] args) {
		Class<TestAnnotation> c = TestAnnotation.class;
		
		//	Class.isAnnotationPresent(class) 当前类是否被注解修饰
		boolean useReport = c.isAnnotationPresent(Report.class);
		System.out.println(useReport ? "当前类被@Report修饰" : "当前类没有被@Report修饰");
		
		//	获取注解实例
		Report r = c.getAnnotation(Report.class);
		System.out.printf("[%s] %s ", r.level().name(), r.value());
		
	}

}
