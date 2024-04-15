package com.ying.tjava.io;

import java.io.*;

public class TestSerial {

	
	
	public static void main(String[] args) {
		//	serialize();
		unserial();
	}

	/**
	 * object 序列化成二进制
	 * 需要实现 Serializable 接口
	 */
	static byte[] serialize() {
		Person p = new Person();
		p.name = "hello";
		
		byte[] result = null;
		
		try (
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				ObjectOutputStream oin = new ObjectOutputStream(bout);
			) {
			oin.writeObject(p);
			oin.flush();
			System.out.println(bout.toString("UTF-8"));
			System.out.println(new String(bout.toByteArray()));
			result = bout.toByteArray();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	/**
	 * 反序列化
	 */
	static void unserial() {
		byte[] data = serialize();
		
		try (ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(data))) {
			Person p = (Person)oin.readObject();
			System.out.println(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Person implements Serializable{
	public String name;

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
	
}
