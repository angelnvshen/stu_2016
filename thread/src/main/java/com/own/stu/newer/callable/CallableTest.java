package com.own.stu.newer.callable;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by CHANEL on 2016/6/7.
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Future<String> future = null;

        future = service.submit(new Callable<String>() {
            public String call() throws Exception {
                return new Random().nextInt()+"" ;
            }
        });
        System.out.println(future.get());


        CompletionService<String> completionService = new ExecutorCompletionService<String>(service);
        for(int i = 0;i<10;i++) {
            final int queue = i;
            completionService.submit(new Callable<String>() {
                public String call() throws Exception {
                    Thread.sleep(100);
                    return new Random().nextInt() + " from " + queue;
                }
            });
        }

        for (int i=0;i<10;i++){
            System.out.println(completionService.take().get());
        }


        service.shutdown();

    }
}
