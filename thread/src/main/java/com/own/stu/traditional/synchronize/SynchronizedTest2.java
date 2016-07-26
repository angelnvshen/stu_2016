package com.own.stu.traditional.synchronize;

/**
 * Created by CHANEL on 2016/6/6.
 */
public class SynchronizedTest2 {
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

    public void main() throws InterruptedException {

        for(int i=0;i<10;i++) {
            synchronized (this) {
                while(!shouldMain) {
                    wait();
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(j + " main  from : " + i);
                }
                shouldMain = false;
                notify();
            }
        }

    }

    public void sub() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                while(shouldMain) {
                    wait();
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(j + " sub  from : " + i);
                }
                shouldMain = true;
                notify();
            }
        }

    }
}