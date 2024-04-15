package com.ying.tjava.collection;

import java.util.Objects;

public class TestMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Person1 {
	private String name;
	private String sex;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Person1(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}
	
	//	使用hashmap需要重写hashCode
	//	重写一般使用Object.hash()
	@Override
	public int hashCode() {
		return Objects.hash(name, sex);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person1 other = (Person1) obj;
		return Objects.equals(name, other.name) && Objects.equals(sex, other.sex);
	} 
	
	
}
