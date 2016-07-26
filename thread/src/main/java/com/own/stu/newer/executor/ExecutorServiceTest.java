package com.own.stu.newer.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        service.schedule(new Runnable() {
            public void run() {
                System.out.println("xxxx");
            }
        },3, TimeUnit.SECONDS);
        service.shutdown();
    }
    public static void main2(String[] args) {
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int k=0;k<10;k++){
            final int task = k;
            service.submit(new Runnable() {
                public void run() {
                    for(int i=0;i<10;i++)
                        System.out.println(Thread.currentThread().getName() +
                                " :  " + i + " from task " + task);
                }
            });
        }
        service.shutdown();
//        service.shutdownNow();
    }
}
