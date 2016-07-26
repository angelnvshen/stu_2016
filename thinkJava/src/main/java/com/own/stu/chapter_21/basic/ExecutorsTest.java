package com.own.stu.chapter_21.basic;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by CHANEL on 2016/6/10.
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i =0;i<10;i++){
            service.submit(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                        System.out.println(Thread.currentThread() + " ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
        try {
            boolean done = service.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println(done);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
