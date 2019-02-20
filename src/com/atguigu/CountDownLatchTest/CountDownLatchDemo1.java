package com.atguigu.CountDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author gbz
 * @date 2019-02-19 21:09
 */
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <7 ; i++) {
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"国被灭");
               countDownLatch.countDown();
            },CountEmnu.foreach_CountEmnu(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"******一同六国");
    }
}
