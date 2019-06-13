package com.stt.thread.part02_concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2个线程进行数据的交互
 * Created by ttshe2 on 2019/6/13.
 */
public class Ch19_ExchangerDemo {

    private static final Exchanger<String> exch = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(()->{
            String a = "a";
            try {
                String b = exch.exchange(a);
                System.out.println("a=="+b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(()->{
            String b = "b";
            try {
                String a = exch.exchange("b");
                System.out.println("b=="+a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
    }
}
