package com.ying.tjava.thread;

import java.util.concurrent.Semaphore;

/**
 * 信号量，确保最多n个线程同时访问，限流
 */
public class TestSemaphore {

	private final Semaphore sem = new Semaphore(3); 
	
	public void testSemaphore() throws InterruptedException {
		//	try doing something
		sem.acquire();
		System.out.println("success!");
		sem.release();
	}
}
