package com.own.stu.newer.condition;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CHANEL on 2016/6/8.
 */
public class BufferQueue {

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private int count, putptr, takeptr ;

    private int length = 100; //length
    private Object[] items = new Object[length];

    BufferQueue(){

    }
    BufferQueue(int size){
        length = size;
        items = new Object[length];
    }
    public void put(Object data) throws InterruptedException {
        lock.lock();
        try{
            while(count == length){
                notFull.await();
            }

            items[putptr] = data;
            if(++putptr == length)
                putptr = 0;

            ++count;
            notEmpty.signal();

        }finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0)
                notEmpty.await();

            Object x = items[takeptr];
            if(++takeptr == length)
                takeptr = 0;

            --count;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final BufferQueue queue = new BufferQueue(10);
        queue.put(1);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=0;i<20;i++){
            service.submit(new Runnable() {
                public void run() {
                    try {
                        Object o = queue.take();
                        System.out.println(Thread.currentThread().getName() + " take " + o);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for(int i=0;i<20;i++){
            final int num = i;
            service.submit(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " put " + num);
                        queue.put(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}


