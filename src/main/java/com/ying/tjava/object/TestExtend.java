package com.ying.tjava.object;

public class TestExtend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testExtend();

	}
	
	//	测试向上转型后的父类能否调用子类方法和属性
	static void testExtend() {
		class Person {
			String name;
			int age;
			String sex;
			
			void run() {
				System.out.println("person run!");
			}
		}
		class Student extends Person{
			int classnumber;
			
			
			
			public int getClassnumber() {
				return classnumber;
			}



			public void setClassnumber(int classnumber) {
				this.classnumber = classnumber;
			}



			@Override
			void run() {
				System.out.println("student run!");
			}
		}
		
		Person p = new Student();
		//p.classnumber = 2;	//	无法访问子类的属性和方法
		p.run();				//	student run! 多态，调用的是子类重写后的run
	}

}
