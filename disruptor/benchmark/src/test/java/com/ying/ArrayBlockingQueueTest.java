package com.ying;

import com.ying.model.Data;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueueTest app = new ArrayBlockingQueueTest();
//        app.test();
    }

    @Test
    public void testBlock() throws InterruptedException {
        Thread.sleep(1000000);
    }

    @Test
    public void test() throws InterruptedException {
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<>(100000000);
        final long startTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        //向容器中添加元素
        Thread thread = new Thread(new Runnable() {

            public void run() {
                long i = 0;
                while (i < Constants.EVENT_NUM_OHM) {
                    Data data = new Data(i, "c" + i);
                    try {
                        queue.put(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                countDownLatch.countDown();
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                int k = 0;
                while (k < Constants.EVENT_NUM_OHM) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("ArrayBlockingQueue costTime = " + (endTime - startTime) + "ms");
                countDownLatch.countDown();
            }
        });
        thread1.start();
        countDownLatch.await();

    }

}
