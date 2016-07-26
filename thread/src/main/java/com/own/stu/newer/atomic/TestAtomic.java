package com.own.stu.newer.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class TestAtomic {
    public static void main(String[] args) {

        final AtomicInteger number = new AtomicInteger(0);

        for(int i = 0;i<10;i++){
            new Thread(new Runnable() {
                public void run() {
                    number.addAndGet(2);
                    System.out.println(Thread.currentThread().getName() + " :  " +number.get());
                }
            }).start();
        }

    }
}
