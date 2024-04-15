package com.ying.tjava.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

/**
 * 实现生产者消费者模型
 */
public class TestWaitAndNotify {

	TaskQueue queue;
	List<Thread> consumersList = new ArrayList<Thread>();
	
	@Test
	public void test() throws InterruptedException {
		queue = new TaskQueue();
		consume(queue);
		produce(queue);
		
		for (Thread t : consumersList) {
			t.join();
		}
	}
	
	public void consume(TaskQueue queue) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			String  name = String.format("Thread-%s", i);
			Thread consumer = new Thread(() -> {
				try {
					String task = queue.getTask(name);
					Thread.sleep(1000);
					System.out.printf("线程[%s]执行任务[%s]成功!\n", name, task);
				} catch (InterruptedException e) {}
			});
			consumer.start();
			consumersList.add(consumer);
			//	consumer.join();
		}
	}

	public void produce(TaskQueue queue) throws InterruptedException {
		Thread producer = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				
				queue.addTask("task " + i);
				// sleep 1 sec;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		producer.start();
		producer.join();
	}
}

/**
 * Thread-safe Queue
 */
class TaskQueue {
	// private final Object qLock = new Object();
	private Queue<String> queue = new LinkedList<String>();

	/**
	 * 产生资源，通知等待状态的线程苏醒
	 * 
	 * @param task
	 */
	public synchronized void addTask(String task) {
		System.out.printf("任务[%s]生产成功!\n", task);
		queue.add(task);
		this.notifyAll();
	}

	/**
	 * 避免死锁,没资源时自动释放锁进入等待状态
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized String getTask(String threadName) throws InterruptedException {
		while (queue.isEmpty()) {
			this.wait();
		}

		System.out.printf("线程[%s]获取任务[%s]成功,开始执行...\n", threadName, queue.peek());
		return queue.poll();
	}
}
