package com.ying.tjava.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;



public class TestFuture {

	/**
	 * 实现Callable接口来获取任务返回值,submit()会返回Future接口， 可以调用Future.get()获取结果，可能会阻塞
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void futureTask() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(4);
		Future<String> future = executor.submit((Callable<String>) new Task("1"));
		System.out.println(future.get());
		executor.shutdown();
	}

	/**
	 * CompletableFuture 提供回调函数，当结果出来时自动调用回调函数，不用阻塞主线程来获取结果
	 * @throws InterruptedException 
	 */
	@Test
	public void testCompletableFuture() throws InterruptedException {
		//	用线程池运行任务，任务带返回值
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			return callTask();
		});
		//	配置回调函数，回调函数参数是任务的输出
		future.thenAccept((result) -> {
			System.out.println("调用回调函数，异步结果出来了: " + result);
		});
		//	配置异常处理
		future.exceptionally((e) -> {
			e.printStackTrace();
			return null;
		});
		System.out.println("不阻塞，直接打印！");
		Thread.sleep(10000);
	}
	
	/**
	 * CompletableFuture 实现串行调用
	 */
	public void testCompletableFuture1() {
		
	}

	public static String callTask() {
		System.out.printf("[%s]执行中...\n", "task");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}
		System.out.printf("[%s]执行完毕!\n", "task");
		return "call return";
	}
}
