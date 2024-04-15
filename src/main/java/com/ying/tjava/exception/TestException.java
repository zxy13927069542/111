package com.ying.tjava.exception;

import java.awt.AWTException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class TestException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		toGBK();	//	[-42, -48, -50, -60]
//		
//		try {
//			testThrows();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		combineExcep();
		
		throwWithThrows();
	}

	static void toGBK() {
		var s = "中文";
		byte[] encode = null;
		try {
			encode = s.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			encode = s.getBytes();
		}
		
		System.out.println(Arrays.toString(encode));
	}
	
	//	test throws
	//	抛出异常后之后的代码会中断执行，finnally中的代码始终会被执行
	static void testThrows() throws IOException {
		try {
			throw new IOException();
		} finally {
			//	Reachable code
			System.out.println("after exception!");
		}
		//	Unreachable code
		//	System.out.println("after exception!");
	}
	
	//	相同处理且无关系的异常可以放在一起
	static void combineExcep() {
		try {
			throw new IOException();			
		} catch (IOException | NumberFormatException e) {	//	相同处理且无关系的异常可以放在一起
			System.out.println("aaa");
		}
	}

	//	使用throw抛出异常但不使用throws声明
	static void throwWithThrows() {
		throw new IllegalArgumentException();
	}
}
