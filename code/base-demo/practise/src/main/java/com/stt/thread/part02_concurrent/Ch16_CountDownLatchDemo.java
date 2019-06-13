package com.stt.thread.part02_concurrent;

import java.util.concurrent.CountDownLatch;


public class Ch16_CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        joinDemo();
        countDownLatchDemo();
    }

    /**
     * 主线程等待前2个线程执行完成后再执行
     */
    public static void joinDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> System.out.println("t1 run ..."));
        Thread t2 = new Thread(() -> System.out.println("t2 run ..."));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("t1 t2 finish");
    }

    /**
     * 使用countDownLatch实现join的功能
     */
    public static void countDownLatchDemo() throws InterruptedException {

        // 设定计数器的值为2
        CountDownLatch c = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            System.out.println("t1 run ...");
            // 计数器的值-1
            c.countDown();
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 run ...");
            c.countDown();
        });
        t1.start();
        t2.start();
        // 阻塞当前线程，如果计数器的值为0，则解除阻塞
        c.await();
        System.out.println("t1 t2 finish");
    }

}