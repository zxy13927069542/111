package com.ying.tjava.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * ReentrantLock可以用于替代synchronized
 * Condition 可以用于实现wait() notifyAll() notify()
 * 使用condition.signalAll()来代替object.notifyAll()
 * 使用condition.await()来替代object.wait()
 * ReentrantLock 可进一步替换成读写锁 ReentrantReadWriteLock
 * ReentrantReadWriteLock是悲观锁，读的过程不许加写锁，默认认为读的过程写会发生不一致，不许加写锁
 */
public class TestReentrantLock {

}

class Count1 {
	private static final Lock lock = new ReentrantLock();
	//	ReentrantLock 可进一步替换成读写锁 ReentrantReadWriteLock
	private static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private static final Lock rLock = rwLock.readLock();
	private static final Lock wLock = rwLock.writeLock();
	
	
	private static int count = 0;

	// 当前对象作为锁，即 this
	// public synchronized void add(int n) {
	// count += n;
	// }

	// 当前方法为静态方法，以 Count.class 为锁
	// public static synchronized add(int n) {
	// count += n;
	// }

	public void add(int n) {
		lock.lock();
		count += n;
		lock.unlock();
	}

	/**
	 * 原子操作，不需要同步
	 * 
	 * @return
	 */
	public int get() {
		return count;
	}
}

/**
 * Thread-safe Queue
 */
class TaskQueue1 {
	private final Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	// private final Object qLock = new Object();
	private Queue<String> queue = new LinkedList<String>();

	/**
	 * 产生资源，通知等待状态的线程苏醒 使用condition.signalAll()来代替object.notifyAll()
	 * 
	 * @param task
	 */
	public void addTask(String task) {
		lock.lock();
		try {
			System.out.printf("任务[%s]生产成功!\n", task);
			queue.add(task);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 避免死锁,没资源时自动释放锁进入等待状态
	 * 使用condition.await()来替代object.wait()
	 * @return
	 * @throws InterruptedException
	 */
	public String getTask(String threadName) throws InterruptedException {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				condition.await();
			}

			System.out.printf("线程[%s]获取任务[%s]成功,开始执行...\n", threadName, queue.peek());
			return queue.poll();
		} finally {
			lock.unlock();
		}
	}
}