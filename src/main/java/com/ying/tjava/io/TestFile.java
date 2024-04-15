package com.ying.tjava.io;

import java.io.*;

/**
 * 运行时的根路径为项目根路径 java_test/
 */
public class TestFile {
	public static void main(String[] args) throws IOException, InterruptedException {
		//	testFile();
		//	createFile();
		//	createTmpFile();
		listFile();
	}
	
	
	static void testFile() throws IOException {
		File f = new File("./resources/testFile.txt");
		System.out.println(f.exists() ? "路径正确" : "路径错误");
		System.out.println(f.isFile() ? "路径正确" : "路径错误");
		
		//	项目根路径: D:\workspace\java_works\java_test\.\resources\testFile.txt
		System.out.println("项目根路径: " + f.getAbsolutePath());
		//	项目根路径: .\resources\testFile.txt
		System.out.println("项目根路径: " + f.getPath());
		//	项目根路径: D:\workspace\java_works\java_test\resources\testFile.txt
		System.out.println("项目根路径: " + f.getCanonicalPath());	
	}

	static void createFile() throws IOException, InterruptedException {
		File f = new File("resources/createdByJava.txt");
		System.out.println(f.createNewFile() ? "创建成功" : "创建失败");
		
		Thread.sleep(10000);	//	休眠10s
		//	f.delete();
		
		//	程序退出时删除
		f.deleteOnExit();
		System.out.println("删除成功");
	}

	static void createTmpFile() {
		try {
			File f = File.createTempFile("tmp-", ".txt", new File("resources/"));
			f.deleteOnExit();
			System.out.println(f.getCanonicalPath());
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 递归输出文件或文件夹
	 */
	static void listFile() {
		File f = new File("./src");
		System.out.println(f.isDirectory() ? "路径正确" : "路径错误");
		listFiles(f, "");
	}
	
	static void listFiles(File f, String prefix) {
		if (f.isFile()) {
			System.out.printf("%s%s\n", prefix, f.getName());
			return;
		}
		
		System.out.printf("%s%s\n", prefix, f.getName());
		prefix = prefix.replace("-", " ");
		prefix += "--";
		for (File f1 : f.listFiles()) {
			listFiles(f1, prefix);
		}
		
		
	}
}
