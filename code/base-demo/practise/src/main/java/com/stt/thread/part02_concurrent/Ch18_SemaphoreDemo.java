package com.stt.thread.part02_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Ch18_SemaphoreDemo {

    // 定义线程数是30
    private static final int THREAD_COUNT=30;
    private static ExecutorService threadPool =
            Executors.newFixedThreadPool(THREAD_COUNT);
    // 定义信号量的计数器大小是10
    private static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
        for(int i = 0;i< THREAD_COUNT;i++){
            int finalI = i;
            threadPool.execute(()->{
                try {
                    // 获取信号量，没有获取到信号量的阻塞
                    System.out.println("thread "+ finalI +" start");
                    s.acquire();
                    System.out.println("thread "+ finalI +" run");
                    TimeUnit.SECONDS.sleep(10);
//                     释放信号量
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
