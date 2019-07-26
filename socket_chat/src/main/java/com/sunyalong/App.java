package com.sunyalong;


import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {

    private static ExecutorService threadPool = new ThreadPoolExecutor(3, 6,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        hashSetTest();

//

//        App app = new App();
//        app.test();

        String s1=new String("abcd");

        String s2=new String("abcd");

        String s3=s1;

        boolean b = s3==s1;

        System.out.println("==运算:s1="+b);

    }


    Integer num = new Integer(0);
    final int THREAD_SIZE = 10;

    final int TASK_SIZE = 100000;


    public void test() throws InterruptedException {



        final CountDownLatch latch = new CountDownLatch(THREAD_SIZE);

        for (int i = 0; i < THREAD_SIZE; i++) {

            new Thread() {

                @Override
                public synchronized void run() {
                    for (int j = 0; j < TASK_SIZE / THREAD_SIZE; j++) {
                            num++;
                        }

                    latch.countDown();
                }

            }.start();

        }

        latch.await();

        System.out.println("num-->" + num);

    }







    private  static void hashSetTest() {
        // 按照hash表的结果
        Set<String> hset = new HashSet<>();
        hset.add("5sd");
        hset.add("5ff");
        hset.add("4rr");
        hset.add("3sd");
        hset.add("2rt");
        System.out.println(hset);


        // 使用自然顺序排序
        Set<String> tset = new TreeSet<>();
        tset.add("5sd");
        tset.add("5ff");
        tset.add("4rr");
        tset.add("3sd");
        tset.add("2rt");
        System.out.println(tset);

        // 按照先进先出的方式排序
        Set<String> lset = new LinkedHashSet<>();
        lset.add("5sd");
        lset.add("5ff");
        lset.add("4rr");
        lset.add("3sd");
        lset.add("2rt");
        System.out.println(lset);
    }
}
