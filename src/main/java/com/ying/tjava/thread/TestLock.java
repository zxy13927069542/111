package com.ying.tjava.thread;

import org.junit.jupiter.api.Test;

public class TestLock {

	/**
	 * 把加锁语句备注掉后会导致结果异常
	 * 在使用synchronized的时候，不必担心抛出异常。因为无论是否有异常，都会在synchronized结束处正确释放锁：
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testSynchronized() throws InterruptedException {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				synchronized (Count.countLock) {
					Count.count += 1;
				}
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				synchronized (Count.countLock) {
					Count.count += 1;
				}
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println(Count.count);

	}

}

/**
 * 线程安全
 */
class Count {
	static final Object countLock = new Object();
	static int count = 0;

	// 当前对象作为锁，即 this
	// public synchronized void add(int n) {
	// count += n;
	// }

	// 当前方法为静态方法，以 Count.class 为锁
	// public static synchronized add(int n) {
	// count += n;
	// }

	public void add(int n) {
		synchronized (countLock) {
			count += n;
		}
	}

	/**
	 * 原子操作，不需要同步
	 * @return
	 */
	public int get() {
		return count;
	}
}
