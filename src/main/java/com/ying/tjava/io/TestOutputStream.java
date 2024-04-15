package com.ying.tjava.io;
import java.io.*;

public class TestOutputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//	testWrite();
		copyFile(args[0], args[1]);
	}

	static void testWrite() {
		try (OutputStream output = new FileOutputStream(new File("resources/output.txt"))) {
			//	覆盖原有内容
			output.write("这是output的输出abcdddd".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * cd com.ying.io
	 * javac TestOutputStream.java
	 * cd ..\..\..
	 * java com.ying.io.TestOutputStream ..\resources\testFile.txt ..\resources\testFile.cp.txt
	 * @param src
	 * @param dst
	 */
	static void copyFile(String src, String dst) {
		try (InputStream input = new FileInputStream(new File(src));
				OutputStream output = new FileOutputStream(new File(dst))) {
			//	方式一
//			byte[] buffer = new byte[1000];
//			input.transferTo(output)
//			while (true) {
//				int n = input.read(buffer);
//				if (n == -1) break;
//				
//				output.write(buffer, 0, n);
//			}
			
			//	方式二
			input.transferTo(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
