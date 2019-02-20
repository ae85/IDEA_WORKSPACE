package com.atguigu.CountDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author gbz
 * @date 2019-02-19 21:09
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"同学离开");
               countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"班长离开");
    }
}
