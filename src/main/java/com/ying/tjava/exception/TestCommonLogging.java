package com.ying.tjava.exception;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestCommonLogging {
	//	静态方法中调用，定义为静态变量，参数也用 TestCommonLogging.class 静态方式
	static final Log log = LogFactory.getLog(TestCommonLogging.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log log = LogFactory.getLog(TestCommonLogging.class);
		log.info("这是一条INFO消息");
		log.warn("这是一条WARN消息");
		log.error("这是一条error消息");
		
		new Person().fun();
	}

}


class Person {
	//	在实例方法中调用，定义为实例变量，参数使用 getClass(), getClass() 子类调用时会返回子类的class
	final Log log = LogFactory.getLog(getClass());
	
	void fun() {
		//	可以携带异常
		log.info("hello", new UnsupportedEncodingException());
	}
}

//在子类中使用父类实例化的log:
class Student extends Person {
 void bar() {
	 //	相当于 getLog(Student.class)
     log.info("bar");
 }
}