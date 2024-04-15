package com.ying.tjava.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class TestThread {

	/**
	 * 1、通过继承Thread类并重写run方法来创建一个线程
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void newThread1() throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("[Thread t] " + i);
				}
			}
		};

		t1.start();

		for (int i = 0; i < 100; i++) {
			System.out.println("[Thread main] " + i);
		}
		// 阻塞直到线程执行完成，类似于golang的waitgroup
		t1.join();
	}

	/**
	 * 2、实现runnable接口并作为参数传递给Thread的构造
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void newThread2() throws InterruptedException {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("[Thread runable] " + i);
			}
		});

		t.start();

		for (int i = 0; i < 100; i++) {
			System.out.println("[Thread main] " + i);
		}
		// 阻塞直到线程执行完成，类似于golang的waitgroup
		t.join();
	}
	
	
	/**
	 * 3、通过线程池来创建线程
	 */
	@Test
	public void newThread3() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(() -> {
			System.out.println("task start...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("task end!");
		});
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 4、通过实现Callable接口创建FutureTask，传递给Thread构造来创建线程
	 * 5、通过函数式编程实现runnable接口
	 * @throws InterruptedException 
	 */
	@Test
	public void newThread4() throws InterruptedException {
		FutureTask<String> future = new FutureTask<>(new Task1());
		new Thread(future).start();
		try {
			String result = future.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 中断用例
	 * @throws InterruptedException
	 */
	@Test
	public void threadInterrupt() throws InterruptedException {
		Thread t = new Thread(() -> {
			for (int i = 0; !Thread.currentThread().isInterrupted() && i < 100000; i++) {
				System.out.println("[Thread runable] " + i);
			}
			System.out.println(Thread.currentThread().isInterrupted() ? "interrupted!" : "end!");
		});
		t.start();
		Thread.sleep(5);
		t.interrupt();
		t.join();
	}

	/**
	 * 中断t1后，t2不会被中断，需要手动中断
	 * 中断后如果线程在等待状态，如 Thread.join() Thread.sleep()，会报InterruptedException
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void interruptWhileJoinning() throws InterruptedException {
		Thread t1 = new Thread1();
		t1.start();
		Thread.sleep(1000);
		t1.interrupt();
		t1.join();
	}
	
	/**
	 * 单元测试下，就算不定义守护线程，也会自动结束
	 * 定义在Main里面的守护线程，main线程结束时也跟着结束
	 * @throws InterruptedException 
	 */
	@Test
	public void daemonThreadInMain() throws InterruptedException {
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
	
	@Test
	public void daemonThread() throws InterruptedException {
		Thread t2 = new Thread2();
//		t2.setDaemon(true);
		t2.start();
		System.out.println("main: wait 3 sec.");
		Thread.sleep(3000);
		System.out.println("main: end.");
	}
}

class Thread1 extends Thread {
	@Override
	public void run() {
		Thread t2 = new Thread2();
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("t1在join下被中断了!");
			e.printStackTrace();
			t2.interrupt();
		}
	}
}

class Thread2 extends Thread {
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("t2执行中!");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
	}
}

class Task1 implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("task start...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task end!");
		return "task end";
	}
	
}
