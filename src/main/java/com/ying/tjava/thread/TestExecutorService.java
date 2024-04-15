package com.ying.tjava.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池 java.util.concurrent.ExecutorService 
 * FixedThreadPool			线程数固定的线程池				Executors.newFixedThreadPool(3) 
 * CachedThreadPool			线程数根据任务动态调整的线程池	Executors.newCachedThreadPool() 
 * SingleThreadExecutor		仅单线程执行的线程池			Executors.newSingleThreadExecutor()
 */
public class TestExecutorService {
//	static ExecutorService executor = Executors.newFixedThreadPool(3);
//	static ExecutorService executor = Executors.newCachedThreadPool();
	static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//	testThreadPool();
		//	schedualedThreadPool();
		//	futureTask();
	}

	public static  void testThreadPool() {
		for (int i = 0; i < 5; i++) {
			executor.submit((Runnable)new Task("Task-" + i));
		}
		/**
		 * shutdown()			等待正在执行的任务先完成再关闭
		 * shutdownNow()		直接关闭
		 * awaitTermination()	则会等待指定的时间让线程池关闭
		 */
		executor.shutdown();
	}
	
	/**
	 * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
                            
	 */
	public static void testCachedThreadPool(int min, int max) {
		//	动态调整线程范围
		executor = new ThreadPoolExecutor(min, max,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
	}
	
	/**
	 * SchedualedThreadPool 可定期反复执行任务，类似于go的Ticker
	 */
	public static void schedualedThreadPool() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);
		//	schedule(Runnable command,long n, TimeUnit unit) n秒后执行一次 
		executor.schedule((Runnable)new Task("Task-1"), 5, TimeUnit.SECONDS);
		//	scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit)
		//	一定延迟后，以指定周期执行任务，不关心任务执行多长
		executor.scheduleAtFixedRate(new Task("Task-2"), 2, 3, TimeUnit.SECONDS);
		//	scheduleWithFixedDelay(Runnable command,long initialDelay,long period,TimeUnit unit)
		//	一定延迟后，以指定周期执行任务，每个任务之间间隔一定的周期
		executor.scheduleWithFixedDelay(new Task("Task-3"), 2, 3, TimeUnit.SECONDS);
		executor.shutdown();
	}
}

class Task implements Runnable, Callable<String> {

	// task name
	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("[%s]执行中...\n", name);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.printf("[%s]执行完毕!\n", name);
	}

	@Override
	public String call() throws Exception {
		System.out.printf("[%s]执行中...\n", name);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.printf("[%s]执行完毕!\n", name);
		return "call return";
	}

}
