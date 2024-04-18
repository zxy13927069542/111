package com.ying.tjava.io;

import java.io.InputStream;
import java.util.Arrays;

public class TestClassPath {

	public static void main(String[] args) {
		readFromClassPath();
	}

	/**
	 *  "/" 表示根目录,这里实际指 bin/
	 *  如果前面不使用 "/" ,则指class文件所在目录
	 */
	static void readFromClassPath() {
		System.out.println(TestClassPath.class.getResource("/log4j2.xml"));
		try (InputStream input = TestClassPath.class.getResourceAsStream("/log4j2.xml")) {
			String contend = new String(input.readAllBytes());
			System.out.println(contend);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
