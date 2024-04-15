package com.ying.tjava.collection;

import java.util.*;

public class TestSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//	set();
		process();
	}

	static void set() {
		Set<String> s = new HashSet<>();
		s.add("aaa");
		System.out.println(s.add("aaa"));
		s.add("bbb");
		s.add("ddd");
		for (String s1 : s) {
			System.out.println(s1);
		}
	}
	
	//	消息去重
	static void process() {
		List<Message> received = List.of(
	            new Message(1, "Hello!"),
	            new Message(2, "发工资了吗？"),
	            new Message(2, "发工资了吗？"),
	            new Message(3, "去哪吃饭？"),
	            new Message(3, "去哪吃饭？"),
	            new Message(4, "Bye")
	        );
		
		Set<Message> set = new HashSet<>(received);
		List<Message> l = List.copyOf(set);
		for (Message m : set) {
			System.out.println(m.getSequence() + " " + m.getText());
		}
	}
}

class Message {
	private int sequence;
	private String text;
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public int hashCode() {
		return Objects.hash(sequence, text);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return sequence == other.sequence && Objects.equals(text, other.text);
	}
	public Message(int sequence, String text) {
		super();
		this.sequence = sequence;
		this.text = text;
	}
	
}