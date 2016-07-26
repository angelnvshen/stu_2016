package com.own.stu.chapter_21.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2016/6/9.
 * 后台线程
 */
public class SimpleDaemon implements Runnable{
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*for(int i=0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemon());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(1000);*/

        ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0;i<10;i++){
            service.submit(new SimpleDaemon());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
















