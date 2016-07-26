package com.own.stu.traditional.synchronize;

/**
 * Created by CHANEL on 2016/6/6.
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        /*for(int i = 0;i<10;i++)
            System.out.println(i + " : main ... ");

        new Thread(new Runnable() {
            public void run() {
                for(int i = 0;i<20;i++)
                    System.out.println(i + " : sub ... ");
            }
        }).start();*/


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

    private static String lock = "LOCK";

    private static void print(String name){
        synchronized (lock){
            for(int i=0;i<name.length();i++)
                System.out.print(name.charAt(i));
            System.out.println();
        }
    }
}
