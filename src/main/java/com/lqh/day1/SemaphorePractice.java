package com.lqh.day1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphorePractice {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        final Semaphore sema = new Semaphore(5);
        for(int i = 0; i < 20; i++) {
            Runnable run = new Runnable() {
                public void run() {
                    try{
                        sema.acquire();     //获取信号量
                        System.out.println(Thread.currentThread().getName() + "获取信号量； 当前剩余信号量： " + sema.availablePermits());
                        Thread.sleep((long)Math.random() * 100000);
                        sema.release();     //休眠随机时间之后释放
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        exec.shutdown();
    }
}
