package com.ying.tjava.collection;

import java.util.*;
import java.io.*;

public class TestProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			properties();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void properties() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		//	当前路径在项目根目录
		//	p.load(new FileInputStream("src/com/ying/collection/aaa.properties"));
		
		//	TestProperties.class.getResourceAsStream 以TestProperties的类路径为当前路径
		p.load(TestProperties.class.getResourceAsStream("aaa.properties"));
		for (Object o : p.keySet()) {
			System.out.println(o + ":" + p.get(o));
		}
		
		System.out.printf("getProperties(\"auto_save_interval\"): %s", p.getProperty("auto_save_interval"));
		
		//	write properties
		p.setProperty("host", "10.8.3.197");
		p.store(new FileOutputStream("src/com/ying/collection/aaa.properties"), null);
	}
}

