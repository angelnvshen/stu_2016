package com.own.stu.newer.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class LockTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    print("oooo");
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    print("xxxx");
                }
            }
        }).start();
    }

    private static Lock lock = new ReentrantLock();

    private static void print(String name){
        lock.lock();
        try {
            for(int i=0;i<name.length();i++)
                System.out.print(name.charAt(i));
            System.out.println();
        }finally {
            lock.unlock();
        }
    }

}
