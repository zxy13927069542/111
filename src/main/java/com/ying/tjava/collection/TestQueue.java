package com.ying.tjava.collection;

import java.util.*;

public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	linkedList();
		//	priorityQueue();
		deque();
	}

	// LinkedList 既实现了List接口，也实现了Queue接口
	static void linkedList() {
		Queue<String> queue = new LinkedList<>();

		// add() 与 offer() 功能相同
		queue.add("hello c");
		queue.add("hello java");
		queue.add("hello golang");
		queue.offer("hello php");

		// 从队首出队
		// poll() 不抛异常
		// remove() 抛异常
		System.out.println(queue.poll());
		System.out.println(queue.remove());

		// 查询当前队首但不出队
		// peek() 不抛异常
		// element() 抛异常
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.element());
	}

	// 优先队列 vip优先
	// PriorityQueue 和 TreeMap 一样，都需要实现Comparable 或传入 Comparator
	static void priorityQueue() {
		Queue<Consumer> queue = new PriorityQueue<>();
		queue.add(new Consumer(Priority.V, "郑晓颖"));
		queue.add(new Consumer(Priority.V, "郑兵颖"));
		queue.add(new Consumer(Priority.IV, "郑速贤"));
		Consumer c1 = queue.poll();
		System.out.printf("consumer.priority: %s consumer.name: %s\n", c1.getP(), c1.getName());
		Consumer c2 = queue.poll();
		System.out.printf("consumer.priority: %s consumer.name: %s\n", c2.getP(), c2.getName());
		
	}

	static void deque() {
		Deque<Consumer> deque = new LinkedList<>();
		//	addFirst()	往队首添加，返回异常 
		//	offerFirst()往队首添加，不返回异常
		//	addLast()	往队尾添加，返回异常
		//	offerLast() 往队尾添加，不返回异常
		deque.addLast(new Consumer(Priority.I, "郑兵颖"));
		deque.addLast(new Consumer(Priority.I, "郑晓颖"));
		deque.addFirst(new Consumer(Priority.I, "郑速贤"));
		deque.addFirst(new Consumer(Priority.I, "郑周珊"));
		
		/**
		 *  peekFirst(): 获取队首但不出队，不抛异常
		 *  getFirst():	获取队首但不出队，抛异常
		 *  peekLast():	获取队尾但不出队，不抛异常
		 *  getLast():	获取队尾但不出队，抛异常
		 */
		Consumer c1 = deque.peekFirst();
		Consumer c2 = deque.getFirst();
		Consumer c3 = deque.peekLast();
		Consumer c4 = deque.getLast();
		System.out.println("peekFirst(): " + c1);
		System.out.println("getFirst(): " + c2);
		System.out.println("peekLast(): " + c3);
		System.out.println("getLast(): " + c4);
		
		/**
		 * 	pollFirst() pollLast()
		 * 	removeFirst() removeLast()
		 */
	}
}

class Consumer implements Comparable<Consumer> {
	private Priority p;
	private String name;
	public Priority getP() {
		return p;
	}
	public void setP(Priority p) {
		this.p = p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Consumer(Priority p, String name) {
		super();
		this.p = p;
		this.name = name;
	}
	
	//	数值越小，优先级越高,从小到大输出
	@Override
	public int compareTo(Consumer o) {
		if (this.p == o.getP()) return 0;
		return this.p.priority > o.getP().priority ? 1 : -1;
	}
	@Override
	public String toString() {
		return "Consumer [p=" + p.priority + ", name=" + name + "]";
	}
	
}

enum Priority {
	I(1), II(2), III(3), IV(4), V(5);

	public final int priority;

	Priority(int j) {
		this.priority = j;
	}
}
