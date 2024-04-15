package com.ying.tjava.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * java.util.concurrent.locks.StampedLock
 * StampedLock可以实现乐观锁和悲观锁
 */
public class TestStampedLock {
}

class Count2{
	private static StampedLock lock = new StampedLock();
	private static int count = 0;
	
	public static void add(int n) {
		long stamp = lock.writeLock();
		count += n;
		lock.unlock(stamp);
	}
	
	public static int caculate() {
		//	获取乐观锁,相当于无锁，不需要释放
		long stamp = lock.tryOptimisticRead();
		int result = count * 100;
		
		//	读的过程中被加了写锁，加悲观锁重新获取
		if (!lock.validate(stamp)) {
			long stamp1 = lock.readLock();
			try {
				return count * 100;
			} finally {
				lock.unlock(stamp1);
			}
		}
		
		return result;
	}
	
}