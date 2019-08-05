package com.ying;

import com.ying.model.Data;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueueTest app = new ArrayBlockingQueueTest();
        app.test();
    }

    @Test
    public void test() {
        System.out.println(1111111);
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(100000000);
        final long startTime = System.currentTimeMillis();
        //向容器中添加元素
        new Thread(new Runnable() {

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
            }
        }).start();

        new Thread(new Runnable() {
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
            }
        }).start();
    }

}
