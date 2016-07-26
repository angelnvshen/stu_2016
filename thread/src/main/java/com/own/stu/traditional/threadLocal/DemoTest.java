package com.own.stu.traditional.threadLocal;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class DemoTest {
    private int j = 0;
    public synchronized void increase(){
        j ++;
        System.out.println(Thread.currentThread().getName() + " , j ++" );
    }
    public synchronized void decrease(){
        j --;
        System.out.println(Thread.currentThread().getName() + " , j --" );
    }

    public static void main(String[] args) {
        final DemoTest demo = new DemoTest();
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    demo.increase();
                }
            }).start();
        }

        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    demo.decrease();
                }
            }).start();
        }
    }
}
