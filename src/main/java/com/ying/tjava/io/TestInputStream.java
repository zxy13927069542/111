package com.ying.tjava.io;

import java.io.*;

public class TestInputStream {
	public static void main(String[] args) {
		
		//	testReadAll();
		testRead();
	}
	
	static void testReadAll() {
		//	try() {} catch() 流会自动关闭
		try( InputStream input = new FileInputStream(new File("resources/testFile.txt")) ) {
			byte[] data = input.readAllBytes();
			String dataStr = new String(data);
				System.out.println(dataStr);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	static void testRead() {
		try (InputStream input = new FileInputStream(new File("resources/testFile.txt"))) {
			StringBuilder s = new StringBuilder();
			while (true) {
				int n = input.read();
				if (n == -1) break;
				//	每次只能读取一个字节，对于多字节的符号会乱码
				s.append((char) n);
			}
			System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
