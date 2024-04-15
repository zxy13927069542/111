package com.ying.tjava.reflect;

import java.lang.reflect.Field;

public class TestClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//	getclass();
		//	getField();
		//	getDeclaredField();
		//	getFields();
		//	getDeclaredFields();
		visitValue();
	}

	
	//	Class实例的获取方式和比较
	static void getclass() throws ClassNotFoundException {
		//	1、通过类名获取
		Class c1 = String.class;
		//	2、通过对象获取
		Class c2 = new String().getClass();
		//	3、通过完整类名获取
		Class c3 = Class.forName("java.lang.String");
		//	Class 类型可以通过 == 比较，因为每种类型的Class全局唯一
		System.out.println(c1 == c2 && c2 == c3 ? "全等" : "不全等");	//	全等
	}
	
	
	//	Class.getField(String name) 只能获取父类子类的public字段
	static void getField() throws NoSuchFieldException, SecurityException {
		Class m = Man.class;
		//	获取父类字段
		//	getField(String name) 只能获取父类、子类的public字段
		System.out.printf("[Field]: %s [Type] %s\n", m.getField("name").getName(), m.getField("name").getType());
		//	private 获取失败
		//	System.out.println("[field] " + m.getField("age").getName());
		
		//	获取子类字段 只能获取public 字段
		System.out.printf("[Field]: %s [Type] %s\n", m.getField("sex").getName(), m.getField("sex").getType());
		//	System.out.printf("[Field]: %s [Type] %s\n", m.getField("height").getName(), m.getField("height").getType());

	}

	//	Class.getDeclaredField(String name) 只能读取子类的所有字段
	static void getDeclaredField() throws Exception {
		Class m = Man.class;
		//	System.out.printf("[Field] %s [Type] %s", 
		//		m.getDeclaredField("name").getName(), m.getDeclaredField("name").getType());
		//	System.out.printf("[Field] %s [Type] %s", 
		//		m.getDeclaredField("age").getName(), m.getDeclaredField("age").getType());
		System.out.printf("[Field] %s [Type] %s\n", 
				m.getDeclaredField("sex").getName(), m.getDeclaredField("sex").getType());
		System.out.printf("[Field] %s [Type] %s\n", 
				m.getDeclaredField("height").getName(), m.getDeclaredField("height").getType());
	}

	//	Class.getFields() 可以获取父类子类全部public字段
	static void getFields() throws Exception {
		Class m = Man.class;
		for (Field f : m.getFields()) {
			System.out.printf("[Field] %s [Type] %s\n", f.getName(), f.getType());
		}
	}

	//	Class.getDeclaredFields() 可以获取子类的全部字段
	static void getDeclaredFields() throws Exception {
		Class m = Man.class;
		for (Field f : m.getDeclaredFields()) {
			System.out.println(f);
		}
	}

	//	使用反射获取属性和修改属性
	static void visitValue() throws Exception {
		Field fieldName = Man.class.getField("name");
		Man m = new Man();
		m.setName("郑晓颖");
		m.setHeight("168");
		
		//	visit value
		System.out.println("current name: " + fieldName.get(m));	//	郑晓颖
		
		//	update value
		fieldName.set(m, "郑兵颖");
		System.out.println("current name: " + fieldName.get(m));	//	郑兵颖
		
		//	update private value
		Field f = Man.class.getDeclaredField("height");
		f.setAccessible(true);
		System.out.println("current height: " + f.get(m));			//	168
		f.set(m, "173");
		System.out.println("current height: " + f.get(m));			//	173
	}

	
}

class Person {
	public String name;
	private String age;
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Person(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person() {}
}

class Man extends Person {
	public String sex;
	private String height;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Man(String name, String age, String sex, String height) {
		super(name, age);
		this.sex = sex;
		this.height = height;
	}
	public Man() {
	}
	
}
