package com.ying.tjava.collection;

import java.util.*;

public class TestList {

	public static void main(String[] args) {
		
		//	listOf();
		//	fori();
		//	iterator();
		//	foreach();
		//	list2Array();
		//	array2List();
		//	findMissingNumber2();
		overrideEquals();
	}
	
	static void testArrayList() {
		List<String> list = new ArrayList<>();
		list.add("helo");
		list.add("world");
		
	}
	
	//	list of 构造出来的list无法修改
	static void listOf() throws java.lang.UnsupportedOperationException {
		List<String> l = List.of("aaa", "bbb");
		l.set(2, "vvv");
	}
	
	// 使用普通for循环进行遍历，不推荐
	static void fori() {
		List<String> l = List.of("aaa", "bbb", "ccc");
		for (int i = 0; i < l.size(); i++ ) {
			System.out.println(l.get(i));
		}
	}
	
	//	使用 Iterator 进行遍历，最高效
	static void iterator() {
		List<String> l = List.of("aaa", "bbb", "ccc");
		Iterator<String> i = l.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}
	
	//	for each 是对 Iterator 写法的优化，推荐
	static void foreach() {
		List<String> l = List.of("aaa", "bbb", "ccc");
		for (String s : l) {
			System.out.println(s);
		}
	}

	//	把list转为数组
	static void list2Array() {
		List<String> l = List.of("郑晓颖", "郑兵颖", "郑周珊");
		//	函数式写法
		String[] arr = l.toArray(String[]::new);
		for (String s : arr) {
			System.out.println(s);
		}
		
		//	普通写法
		String[] arr1 = l.toArray(new String[l.size()]);
		for (String s : arr1) {
			System.out.println(s);
		}
	}

	//	数组转为list
	static void array2List() {
		String[] arr = {"郑晓颖", "郑兵颖", "郑周珊"};
		//	构造出来的list无法修改，只能读取
		List<String> l = List.of(arr);
		for (String s : l) {
			System.out.println(s);
		}
		
		List<String> l1 = Arrays.asList(arr);
		for (String s : l1) {
			System.out.println(s);
		}
	}

	//	list练习1
	static void findMissingNumber() {
		int start = 10;
		int end = 20;
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			l.add(i);
		}
		int removed = l.remove((int)(Math.random() * l.size()));
		
		int temp = l.get(0);
		var found = 0;
		for (int i : l) {
			if (temp != i) {
				found = temp;
				break;
			}
			
			temp++;
		}
		System.out.println("删除的数字: " + removed);
		System.out.println("找到的数字: " + found);
		System.out.println(removed == found ? "成功" : "失败");
		
	}
	
	//	list练习2
	static void findMissingNumber2() {
		int start = 10;
		int end = 20;
		List<Integer> l = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			l.add(i);
		}
		int removed = l.remove((int)(Math.random() * l.size()));
		//	洗牌
		Collections.shuffle(l);
		
		int found = 0;
		int[] arr = new int[end + 1];
		for (int i : l) {
			arr[i] = i;
		}
		for (int i = start; i <= end; i++) {
			if (arr[i] == 0) {
				found = i;
			}
		}
		System.out.println("删除的数字: " + removed);
		System.out.println("找到的数字: " + found);
		System.out.println(removed == found ? "成功" : "失败");
		
	}

	//	重写Equals
	static void overrideEquals() {
		List<Person> list = List.of(
	            new Person("Xiao", "Ming", 18),
	            new Person("Xiao", "Hong", 25),
	            new Person("Bob", "Smith", 20)
	        );
	        boolean exist = list.contains(new Person("Bob", "Smith", 20));
	        System.out.println(exist ? "测试成功!" : "测试失败!");
	}
	
	//	生成一个冒泡排序
	static void bubbleSort(int[] arr) {
		
	}
}

class Person {
    String firstName;
    String lastName;
    int age;
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Person p) {
			return Objects.equals(p.firstName, this.firstName) &&
					Objects.equals(p.lastName, this.lastName) &&
					this.age == p.age;
		} else return false;
	}

}
