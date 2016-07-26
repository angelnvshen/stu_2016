package com.own.stu.traditional.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by CHANEL on 2016/6/6.
 */
public class TimerTest {

    private static int i = 0;

    public static void main(String[] args) {

        class MyTimeTask extends TimerTask{

            @Override
            public void run() {
                System.out.println("bombing ... ");
                new Timer().schedule(new MyTimeTask(), 2000 + (i++)%2*1000);
            }
        }

        /*new Timer("Bom").schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("bombing ... ");
            }
        }, 3000, 1000*//*+(i++)%2*1000*//*);*/

        new Timer("Bom").schedule(new MyTimeTask(), 1000);

        int j = 0;
        while(true){
            System.out.println( j++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
