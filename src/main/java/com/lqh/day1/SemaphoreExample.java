package com.lqh.day1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println("这是我第一次提交");
        final Semaphore semaphore = new Semaphore(5);
        for(int i = 0; i < 20; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        //只有在semaphore信号量还有的情况下才能获取到
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " : 拿到信号量   --- 剩余信号量： " + semaphore.availablePermits());
                        //每个线程休息10秒
                        Thread.sleep(10000);
                        //线程休息10秒之后释放信号量，其他线程可以获取到释放的信号量
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        exec.shutdown();
    }
}
