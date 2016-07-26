package com.own.stu;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class ConcurrentTest{

    static class BoilWater implements Callable<String>{

        public String call() throws Exception {
            Thread.sleep(5000);
            return "水烧好了 ...  ";
        }
    }

    public static void main( String[] args ) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit( new BoilWater());

        while(!future.isDone()){
            Thread.sleep(4000);
            System.out.println("水还没有烧好呢 ");
        }

        System.out.println( future.get() );
    }
}
