package com.ying.tjava.core;

import java.beans.*;
import java.util.Arrays;
import java.util.StringJoiner;

public class TestString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[] scores = new int[] { 88, 77, 51, 66 };
        Score s = new Score(scores);
        s.printScores();
        scores[2] = 99;
        s.printScores();
        
        //	Test StringBuilder
        System.out.println("\n[Test StringBuilder]");
        testStringBuilder();
        buildSql("employee", new String[]{ "name","position", "salary" });
        
        //	Test StringJoiner
        System.out.println("\n[Test StringJoiner]");
        testStringJoiner();
        
        //	Test JavaBean
        System.out.println("\n[Test JavaBean]");
        testJavaBean();
	}
	
	
	static void testStringBuilder() {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("哈哈哈").append("，本大爷来了！");
		System.out.println(sb);
	}
	
	//	generate a insert sql
	static void buildSql(String table, String[] columns) {
		String s = "";
		for (String tmp : columns) {
			s += "?,";
		}
		s = s.substring(0, s.length() - 1);
		StringBuilder sb = new StringBuilder(
				String.format("INSERT INTO %s (%s) VALUES (%s)", 
						table, String.join(",", columns), s));
		System.out.println(sb);
	}
	
	static void testStringJoiner() {
		String[] names = {"Bob", "Alice", "Grace"};
		var sj = new StringJoiner(",", "hello ", "!");	//	分隔符，开头，结尾  Hello Bob, Alice, Grace!
		for (String v : names) {
			sj.add(v);
		}
		System.out.println(sj);
		
		//	build a query sql
		var sql = new StringJoiner(",", "SELECT ", String.format(" FROM %s;", "employee"));
		for (String name : names) {
			sql.add(name);
		}
		System.out.println(sql);
	}

	static void testJavaBean() {
		class Person{

			private String name;
		    private int age;

		    public String getName() {
		        return name;
		    }

		    public void setName(String name) {
		        this.name = name;
		    }

		    public int getAge() {
		        return age;
		    }

		    public void setAge(int age) {
		        this.age = age;
		    }
		};
		try {
			BeanInfo bean = Introspector.getBeanInfo(Person.class);
			for (PropertyDescriptor pd : bean.getPropertyDescriptors()) {
				System.out.println(pd.getName());
				System.out.println(pd.getReadMethod());
				System.out.println(pd.getWriteMethod());
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}

class Score {
	private int[] scores;

	public Score(int[] scores) {
		// 两种方式都可以
		// this.scores = Arrays.copyOf(scores, scores.length);
		this.scores = scores.clone();
	}

	public void printScores() {
		System.out.println(Arrays.toString(scores));
	}
}