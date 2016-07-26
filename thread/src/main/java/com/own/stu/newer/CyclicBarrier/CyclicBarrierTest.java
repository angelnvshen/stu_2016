package com.own.stu.newer.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CHANEL on 2016/6/8.
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        final CyclicBarrier barrier = new CyclicBarrier(3);

        for(int i=0;i<3;i++){
            service.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(3000));
                        System.out.println(Thread.currentThread().getName() + " arrived A ");
                        barrier.await();
                        Thread.sleep(new Random().nextInt(3000));
                        System.out.println(Thread.currentThread().getName() + " arrived B ");
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}
