package com.ying.tjava.collection;

import java.util.*;

/**
 * 使用TreeMap有两种方式，因为TreeMap是有序的，所以KEY需要实现比较大小的接口
 * 方式一: 构造TreeMap时传入 Comparator接口 的匿名内部类
 * 方式二: Key实现Comparable接口
 */
public class TestTreeMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			treeMapByComparable();
		//	treeMapByComparator();
		
	}

	static void treeMapByComparable() {
		Map<Person2, Integer> map = new TreeMap<>();
		map.put(new Person2("369", 36), 36);
		map.put(new Person2("368", 64), 38);
		map.put(new Person2("367", 52), 38);
		
		for (Person2 p : map.keySet()) {
			System.out.println(p.getScore());
		}
	}

	static void treeMapByComparator() {
		Map<Person2, Integer> map = new TreeMap<>(new Comparator<Person2>() {

			@Override
			public int compare(Person2 o1, Person2 o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		map.put(new Person2("369", 36), 36);
		map.put(new Person2("368", 64), 38);
		map.put(new Person2("367", 52), 38);
		
		for (Person2 p : map.keySet()) {
			System.out.println(p.getName());
		}
	}
}

//	方式2：Person2 需要实现 Comparable<Person2>
//	根据分数大小进行排序
class Person2 implements Comparable<Person2>{

	private String name;
	private int score;
	
	public Person2(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	public String getName() {return name;}

	@Override
	public int compareTo(Person2 o) {
		if (score == o.getScore()) return 0;
		//	score > o.getScore() ? 1 : -1 正序 从小到大
		//	score > o.getScore() ? -1 : 1 负序 从大到小
		return score > o.getScore() ? 1 : -1;	//	正序 从小到大
	}
}
