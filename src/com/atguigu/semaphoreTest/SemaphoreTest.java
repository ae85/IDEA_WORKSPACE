package com.atguigu.semaphoreTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author gbz
 * @date 2019-02-20 11:11
 * 六个车抢占3个资源
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个车位

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//抢占
                    System.out.println(Thread.currentThread().getName()+"站到车位");
                    try {
                        TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                   // System.out.println(Thread.currentThread().getName()+"离开车位");
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
