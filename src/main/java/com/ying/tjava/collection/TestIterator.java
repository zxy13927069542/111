package com.ying.tjava.collection;

import java.util.*;

/**
 * 使用for each 遍历，需要实现Iterable接口
 */
public class TestIterator {

	public static void main(String[] args) {
		
		testReverseList();
	}

	static void testReverseList() {
		ReverseList<String> l = new ReverseList<>();
		l.add("hello1");
		l.add("hello2");
		l.add("hello3");
		for (String s : l) {
			System.out.println(s);
		}
	}
}

/**
 * 实现Iterable接口
 * @param <T>
 */
class ReverseList<T> implements Iterable<T> {
	private List<T> list = new ArrayList<>();
	
	public void add(T t) {
		list.add(t);
	}

	@Override
	public Iterator<T> iterator() {
		return new ReverseIterator<T>();
	}
	
	class ReverseIterator<T> implements Iterator<T> {
		//	内部类需要通过 ReverseList.this.list.size()这种方式获取父类属性
		//	内部类隐含了父类的链接
		private int index = ReverseList.this.list.size() - 1;
		
		@Override
		public boolean hasNext() {
			return index >= 0;
		}

		@Override
		public T next() {
			return (T) ReverseList.this.list.get(index--);
		}
		
	}
}


