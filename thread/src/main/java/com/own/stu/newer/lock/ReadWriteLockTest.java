package com.own.stu.newer.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final QueueOwn queue = new QueueOwn();
        for(int i = 0;i<2;i++){
            final int num = i;
            new Thread(new Runnable() {
                public void run() {
                    queue.put(num);
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {
                    queue.get();
                }
            }).start();
        }
    }
}
class QueueOwn{
    Object data;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void put(Object data){
        lock.writeLock().lock();
        this.data = data;
        System.out.println(Thread.currentThread().getName() + " put : " + data);
        lock.writeLock().unlock();
    }

    public void get(){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "get : " + data);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
    }
}

class Cache{
    private Map<String, String> map = new HashMap<String, String>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(String key){

        lock.readLock().lock();
        if(map.get(key) == null){
            lock.readLock().unlock();
            lock.writeLock().lock();
            if(map.get(key) == null){
                map.put(key, "22");
            }
            lock.readLock().lock();
            lock.writeLock().unlock();
        }
        map.get(key);
        lock.readLock().unlock();
    }
}
