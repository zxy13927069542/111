package com.ying.tjava.thread;

public class TestThreadLocal {

}

class User {
	String token;
	int userId;
	String userName;
	public User(String token, int userId, String userName) {
		super();
		this.token = token;
		this.userId = userId;
		this.userName = userName;
	}
	
}

class UserContext implements AutoCloseable {
	private static ThreadLocal<User> context = new ThreadLocal<>();

	public void set(User u) {
		context.set(u);
	}
	
	public static User get() {
		return context.get();
	}
	
	@Override
	public void close() {
		context.remove();
	}}

class Task2 implements Runnable {

	private String taskName;
	
	public Task2(String name) {
		taskName = name;
	}
	
	@Override
	public void run() {
		User user = new User("token", 0, "zxy");
		try (UserContext ctx = new UserContext()) {
			ctx.set(user);
			
		} 
		
	}
	
}
