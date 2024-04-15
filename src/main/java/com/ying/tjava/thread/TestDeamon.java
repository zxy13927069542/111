package com.ying.tjava.thread;

/**
 * 单元测试下，就算不定义守护线程，也会自动结束
 */
public class TestDeamon {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 1000000; i++) {
				System.out.println("Thread-1: running...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.setDaemon(true);
		t.start();
		System.out.println("main: wait 3 sec.");
		Thread.sleep(3000);
		System.out.println("main: end.");
	}

}
