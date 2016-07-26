package com.own.stu.newer.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CHANEL on 2016/6/8.
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        final CountDownLatch cOrder = new CountDownLatch(1);
        final CountDownLatch cAnswer = new CountDownLatch(3);


        for(int i=0;i<3;i++){
            service.submit(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() +
                                " waiting gun shot ... ");

                        cOrder.await();

                        System.out.println(Thread.currentThread().getName() +
                                " heard gun shot ... ");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() +
                                " running ...  ");
                        cAnswer.countDown();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println(Thread.currentThread().getName() +
                " beginning to shut ... ");

        cOrder.countDown();

        System.out.println(Thread.currentThread().getName() +
                " waiting after running ... ");

        cAnswer.await();
        System.out.println(Thread.currentThread().getName() +
                " get results ");

        service.shutdown();
    }
}
