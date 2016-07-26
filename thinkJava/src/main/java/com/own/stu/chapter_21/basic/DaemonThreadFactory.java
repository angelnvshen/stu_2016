package com.own.stu.chapter_21.basic;

import java.util.concurrent.ThreadFactory;

/**
 * Created by CHANEL on 2016/6/9.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
