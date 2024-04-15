package com.ying.tjava.thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedRateExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                // 模拟任务执行时间超过1秒
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Task executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // 使用scheduleAtFixedRate方法以固定的速率触发任务
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}