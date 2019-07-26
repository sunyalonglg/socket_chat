package com.sunyalong;

import java.util.concurrent.CountDownLatch;

public class TestThreadImpl  implements Runnable{

    volatile static int num =0;

    volatile static  int THREAD_SIZE = 10;

    volatile static int TASK_SIZE = 100000;
    static CountDownLatch latch = new CountDownLatch(THREAD_SIZE);

    @Override
    public void run() {
        for (int j = 0; j < TASK_SIZE / THREAD_SIZE; j++) {
            synchronized (this) {
                num++;
            }

        }
        latch.countDown();
    }
}
