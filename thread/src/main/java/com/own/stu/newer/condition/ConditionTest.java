package com.own.stu.newer.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by CHANEL on 2016/6/8.
 */
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {

        final Business business = new Business();
        new Thread(new Runnable() {

            public void run() {
                try {
                    business.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        business.main();
    }

}
    class Business{

        private boolean shouldMain = false;

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void main() throws InterruptedException {

            for(int i=0;i<10;i++) {
                lock.lock();
                while(!shouldMain) {
                    condition.await();
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(j + " main  from : " + i);
                }
                shouldMain = false;
                condition.signal();
                lock.unlock();
            }

        }

        public void sub() throws InterruptedException {

            for (int i = 0; i < 10; i++) {
                lock.lock();
                while(shouldMain) {
                    condition.await();
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(j + " sub  from : " + i);
                }
                shouldMain = true;
                condition.signal();
                lock.unlock();
            }

        }
}
