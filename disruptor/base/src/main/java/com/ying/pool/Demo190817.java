package com.ying.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo190817 {

    public static void main(String[] args) {
        new Demo190817().testWithExecutors();
    }

    public void testWithExecutors() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 200; i++) {
            executorService.execute(new Demo190817.MyRunnable(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("TIME IS" + (end - start));
    }

    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            new Thread(new MyRunnable(i)).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("TIME IS" + (end - start));
    }

    static class MyRunnable implements Runnable {
        private int id;

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("threadpool: task id is " + id + Thread.currentThread().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
