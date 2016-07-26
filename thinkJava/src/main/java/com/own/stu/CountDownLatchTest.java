package com.own.stu;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CHANEL on 2016/7/13.
 */
public class CountDownLatchTest {
    private static Random random = new Random();
    static class Runner implements Runnable{

        private CountDownLatch readyLatch;
        private CountDownLatch startLatch;
        private String name;

        public Runner(String name, CountDownLatch readyLatch, CountDownLatch startLatch) {
            this.readyLatch = readyLatch;
            this.startLatch = startLatch;
            this.name = name;
        }

        public void run() {
            int prepareTime = random.nextInt(1000);
            System.out.println("运动员 " + name + " 号 需要等待" + prepareTime + " 时间");
            try {
                Thread.sleep(prepareTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("运动员 " + name + " 号 已准备好了");
            readyLatch.countDown();

            try {
                startLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("运动员 " + name + " 号 开始跑了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch readyLatch = new CountDownLatch(5);
        CountDownLatch startLatch = new CountDownLatch(1);

        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0;i<5;i++){
            service.submit(new Runner(i + "", readyLatch, startLatch));
        }

        readyLatch.await();

        startLatch.countDown();
        System.out.println("裁判：所有运动员准备完毕，开始...");
    }
}
