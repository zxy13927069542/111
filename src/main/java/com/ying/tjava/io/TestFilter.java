package com.ying.tjava.io;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * 装饰者模式
 */
public class TestFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		filter();
	}

	static void filter() {
		try (CountInputStream in = new CountInputStream(
				new FileInputStream(
						new File("resources/testFile.txt")
						)
				)
			) {
			
			System.out.println("文件大小: " + in.getTotal());
			byte[] buf = new byte[in.getTotal() / 2];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			while (true) {
				int len = in.read(buf, 0, buf.length);
				System.out.println("已读取: " + in.getCount());
				System.out.println("剩余: " + in.getLeft());
				if (len == -1) break;
				
				output.write(buf, 0, len);
				output.flush();
			}
			
			System.out.println(output.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class CountInputStream extends FilterInputStream {

	private int count = 0;
	private int total = 0;
	private int left = 0;
	
	public int getCount() {
		return count;
	}

	public int getTotal() {
		return total;
	}

	public int getLeft() {
		return left;
	}

	public CountInputStream(InputStream in) throws IOException {
		super(in);
		total = in.available();
		left = in.available();
	}
	
	public int read(byte[] b, int off, int len) throws IOException {
       int n = in.read(b, off, len);
       if (n != -1) count += n;
       left = in.available();
       return n;
    }
}