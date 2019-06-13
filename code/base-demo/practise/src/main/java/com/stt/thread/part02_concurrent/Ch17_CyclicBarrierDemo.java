package com.stt.thread.part02_concurrent;

import java.util.concurrent.CyclicBarrier;

public class Ch17_CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        cyclicBarrierDemo();
    }

    public static void cyclicBarrierDemo() throws InterruptedException {
        // 定义计数器带大小为2
//        CyclicBarrier c = new CyclicBarrier(2);
        CyclicBarrier c = new CyclicBarrier(2,()->{
            // 计数器达到0，在所有线程还没有解除阻塞之前执行
            System.out.println("CyclicBarrier run");
        });

        Thread t1 = new Thread(()->{
            System.out.println("t1 start");
            try {
                // 阻塞，计数器-1
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("t2 finish");
        });

        Thread t2 = new Thread(()->{
            System.out.println("t2 start");
            try {
                // 当计数器的值为0，解除阻塞
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("t2 finish");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

//结果
//t1 start
//t2 start
//CyclicBarrier run
//t2 finish
//t2 finish